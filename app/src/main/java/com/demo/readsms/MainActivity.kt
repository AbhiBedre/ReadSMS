package com.demo.readsms

import android.Manifest
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.demo.readsms.extensions.*
import com.demo.screenusage.ScreenUsage
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern


class MainActivity : AppCompatActivity() {
    var contactList: ArrayList<SMSModel> = ArrayList()
    private val REQUEST_SMS_PERMISSION = 10
    private val PROJECTION = arrayOf(
        ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
        ContactsContract.Contacts.DISPLAY_NAME,
        ContactsContract.CommonDataKinds.Phone.NUMBER,
        ContactsContract.RawContacts.ACCOUNT_TYPE,
    )

    lateinit var adapter: SMSAdapter
    lateinit var adapterWithAccountFilter: SMSAdapter
    var regEx: Pattern = Pattern.compile("[a-zA-Z0-9]{2}-[a-zA-Z0-9]{6}")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = SMSAdapter(this)
        adapterWithAccountFilter = SMSAdapter(this, isBankFilter = true)
        rv_sms.adapter = adapter
        rv_smswithFilter.adapter = adapterWithAccountFilter

        btn_getSMS.setOnClickListener {
            if (doesUserHavePermission()) {
                attachContactList()
            } else {
                requestPermission()
            }
        }

        Log.i("TAG", "ScreenUsage: ${ScreenUsage.retrieveScreenUsage()}")
    }

    override fun onResume() {
        super.onResume()
        ScreenUsage.screenOn(this.javaClass.simpleName)
    }

    override fun onPause() {
        super.onPause()
        ScreenUsage.screenOff(this.javaClass.simpleName)
    }

    private fun attachContactList() {
        val smsList = getSMSList()
        adapter.submitList(smsList)

        val appList = mutableSetOf<String>()
        getSMSForCheckAppInstalled().forEach {
            appList.add(appMap[it.app_name] ?: it.app_name)
        }
        tv_appNames.text = "App present in SMS: ${appList}"

        val smsSet: Set<String> = smsList.map { it.address.substring(3, it.address.length) }.toSet()
        adapterWithAccountFilter.submitFilterList(smsSet, smsList as ArrayList<SMSModel>)
    }

    private fun isBankTransactionMSG(body: String): Boolean {
        return (body.contains("credited", ignoreCase = true)
                || body.contains("debited", ignoreCase = true)
                || body.contains("withdrawal", ignoreCase = true)
                || body.contains("transferred", ignoreCase = true)
                || body.contains("cr", true)
                || body.contains("deposited", true)
                || body.contains("deposit", true)
                || body.contains("received", true)
                || body.contains("Paid", true)
                || body.contains("withdrawn", true)
                || body.contains("spent", true)
                || body.contains("paying", true)
                || body.contains("payment", true)
                || body.contains("deducted", true)
                || body.contains("debited", true)
                || body.contains("purchase", true)
                || body.contains("dr", true)
                || body.contains("with salary", true)
                || body.contains("INR", true)
                || body.contains("txn", true)
                || body.contains("transfer", true)
                && (!body.contains("We are pleased to inform that", true)
                && !body.contains("has been opened", true)
                && !body.contains("otp", true)
                && !body.contains("failed", true)
                && !body.contains("emi", true))
                )
    }

    private fun doesUserHavePermission(): Boolean {
        val readContactResult: Int =
            checkCallingOrSelfPermission(Manifest.permission.READ_SMS)
        return readContactResult == PackageManager.PERMISSION_GRANTED
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_SMS_PERMISSION && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            attachContactList()
        } else {
            Toast.makeText(
                this,
                "The app was not allowed to write in your storage",
                Toast.LENGTH_LONG
            ).show()
            finish()
        }
    }

    private fun requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(
                arrayOf(
                    android.Manifest.permission.READ_SMS
                ),
                REQUEST_SMS_PERMISSION
            )
        } else {
            attachContactList()
        }
    }

    private fun getSMSList(): List<SMSModel> {
        val lstSms: MutableList<SMSModel> = ArrayList()
        try {
            val projection = arrayOf("_id", "address", "body", "date")
            val cursor: Cursor? =
                contentResolver.query(
                    Uri.parse("content://sms/inbox"),
                    projection,
                    null,
                    null,
                    null
                )

            if (cursor?.moveToFirst() == true) { // must check the result to prevent exception
                val index_Address: Int = cursor.getColumnIndex("address")
                val index_Body: Int = cursor.getColumnIndex("body")
                val index_Date: Int = cursor.getColumnIndex("date")
                do {
                    val address: String = cursor.getString(index_Address)
                    val body: String = cursor.getString(index_Body)
                    val date: Long = cursor.getLong(index_Date)
                    if (checkSenderIsValid(sender = address) && isBankTransactionMSG(body) && !body.contains(
                            "otp",
                            true
                        )
                    ) {
                        lstSms.add(
                            SMSModel(
                                address = address,
                                body = body,
                                date = millisToDate(date),
                                avlBalance = getAvailableBalance(body),
                                balance = getAmount(body).toDouble(),
//                                accNumber = "Acc No: ${getAccountNumber(body)}",
                                cardType = findCreditCardOrDebitCard(body, address),
//                                refNumber = "Ref Number: ${getRefComanNumber(body)}",
                                transactionType = transactionType(body)
                            )
                        )
//                        extractMerchantNameFromSMS(body)
                    }
                    Log.i("TAG", "getSMSList: ${cursor.getString(index_Address)}")
                } while (cursor.moveToNext())
            } else {
                // empty box, no SMS
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return lstSms
    }

    private fun getSMSForCheckAppInstalled(): List<SMSAppModel> {
        val lstSms: MutableList<SMSAppModel> = ArrayList()
        try {
            val projection = arrayOf("_id", "address", "person", "body", "date", "type")
            val cursor: Cursor? =
                contentResolver.query(
                    Uri.parse("content://sms/inbox"),
                    projection,
                    null,
                    null,
                    null
                )

            if (cursor?.moveToFirst() == true) { // must check the result to prevent exception
                val index_Address: Int = cursor.getColumnIndex("address")
                val index_Person: Int = cursor.getColumnIndex("person")
                val index_Body: Int = cursor.getColumnIndex("body")
                val index_Date: Int = cursor.getColumnIndex("date")
                val index_Type: Int = cursor.getColumnIndex("type")
                do {
                    val address: String = cursor.getString(index_Address)
                    val intPerson: Int = cursor.getInt(index_Person)
                    val body: String = cursor.getString(index_Body)
                    val date: Long = cursor.getLong(index_Date)
                    val int_Type: Int = cursor.getInt(index_Type)
                    if (isAppNameContains(body)) {
                        lstSms.add(
                            SMSAppModel(app_name = address.substring(3, address.length), body)
                        )
                    }
                } while (cursor.moveToNext())
            } else {
                // empty box, no SMS
            }
        } catch (e: Exception) {

        }
        return lstSms
    }

    companion object {
        val appMap = HashMap<String, String>().also {
            it["AcePro"] = "A23"
            it["RMYCRL"] = "RummyCircle"
            it["RUCULT"] = "RummyCulture"
            it["ATOTHIR"] = "A23"
        }
    }

    private fun extractMerchantNameFromSMS(mMessage: String) {
        try {
            val regEx: Pattern =
                Pattern.compile("(?i)(?:\\sInfo.\\s*)([A-Za-z0-9*]*\\s?-?\\s?[A-Za-z0-9*]*\\s?-?\\.?)")
            // Find instance of pattern matches
            val m: Matcher = regEx.matcher(mMessage)
            if (m.find()) {
                var mMerchantName: String = m.group()
                mMerchantName =
                    mMerchantName.replace("^\\s+|\\s+$".toRegex(), "") //trim from start and end
                mMerchantName = mMerchantName.replace("Info.", "")
                Log.e("TAG", "MERCHANT NAME : $mMerchantName")
            } else {
                Log.e("TAG", "MATCH NOTFOUND")
            }
        } catch (e: Exception) {
            Log.e("TAG", e.toString())
        }
    }

    private fun isAppNameContains(sms: String): Boolean {
        return (sms.contains("dream11", ignoreCase = true)
                || sms.contains("My11Circle", ignoreCase = true)
                || sms.contains("RummyCircle", ignoreCase = true)
                || sms.contains("RummyCulture", ignoreCase = true)
                || sms.contains("A23", ignoreCase = true)
                || sms.contains("Ace2Three", true)
                )
    }
}

private fun millisToDate(currentTime: Long): String {
    val finalDate: String
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = currentTime
    val date = calendar.time
    finalDate = date.toString()
    return finalDate
}

data class SMSModel(
    val address: String,
    val body: String,
    val avlBalance: Double = 0.0,
    val balance: Double = 0.0,
    val cardType: String = "",
    val accNumber: String = "",
    val transactionType: String = "",
    val refNumber: String = "",
    val date: String = ""
)

data class SMSAppModel(
    val app_name: String,
    val body: String
)