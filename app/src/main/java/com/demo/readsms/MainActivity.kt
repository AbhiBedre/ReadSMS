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
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.io.StringWriter
import java.util.*
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
    val competitorAppList: MutableList<SMSAppModel> = arrayListOf()

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

        /*val fileInString: String =
            applicationContext.assets.open("rules.json").bufferedReader().use { it.readText() }
        val b: String = mo17213b(applicationContext.assets.open("rules.json"), null as OutputStream?)
        Log.i("TAG", "fileInString: ${b}")*/
    }

    @Throws(IOException::class)
    fun mo17213b(inputStream: InputStream, outputStream: OutputStream?): String {
        val bArr = ByteArray(1024)
        val stringWriter = StringWriter()
        while (true) {
            val read = inputStream.read(bArr)
            if (read == -1) {
                return stringWriter.toString()
            }
            try {
                stringWriter.write(String(bArr), 0, read)
            } catch (unused: StringIndexOutOfBoundsException) {
                val str = String(bArr)
                stringWriter.write(str, 0, str.length)
            }
        }
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
        val cal1000: Calendar = GregorianCalendar().apply { add(Calendar.DAY_OF_MONTH, -1000) }
        val cal30: Calendar = GregorianCalendar().apply { add(Calendar.DAY_OF_MONTH, -30) }
        val cal60: Calendar = GregorianCalendar().apply { add(Calendar.DAY_OF_MONTH, -60) }
        val cal90: Calendar = GregorianCalendar().apply { add(Calendar.DAY_OF_MONTH, -90) }
        fun smsData(cal: Calendar) {
            val smsFilteredList = smsList.filter {
                (getDateFromMilliseconds(it.date) >= getDateFromMilliseconds(cal.timeInMillis)
                        && getDateFromMilliseconds(it.date) <= Date())
            }
            Log.i("TAG", "attachContactList: ${smsFilteredList.size}")
            adapter.submitList(smsFilteredList)

            val smsSet: Set<String> = smsFilteredList.map { it.bankName }.toSet()
            adapterWithAccountFilter.submitFilterList(
                smsSet,
                smsFilteredList as ArrayList<SMSModel>
            )
        }

        smsData(cal1000)
        btn30Days.setOnClickListener {
            smsData(cal30)
        }
        btn60Days.setOnClickListener {
            smsData(cal60)
        }
        btn90Days.setOnClickListener {
            smsData(cal90)
        }

        val appList = mutableSetOf<String>()
        competitorAppList.forEach {
            appList.add(appMap[it.app_name] ?: it.app_name)
        }
        tv_appNames.text = "App present in SMS: ${appList}"
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

    fun isBankPromotionMSG(body: String): Boolean {
        return (!body.contains("We are pleased to inform that", true)
                && !body.contains("has been opened", true)
                && !body.contains("otp", true)
                && !body.contains("failed", true)
                && !body.contains("emi", true)
                && !body.contains("due", true)
                && !body.contains("repayment", true)
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
                    if (checkSenderIsValid(sender = address)
                        && isBankTransactionMSG(body)
                        && isBankPromotionMSG(body)
                    ) {
                        /*val regex =
                            Pattern.compile("(?i)X+(\\d{3,4}).*debited.*(?:INR|Rs)[\\.,\\s]?([\\d,]*\\.?\\d{0,2}).*?([\\S]{3})\\*\\d*\\**(.*?)(?:\\*|\\.\\s*the avail).*bal.*(?:INR|Rs)[\\.,\\s]?([\\d,\\-]*\\.?\\d{2})")
                        val m = regex.matcher(body.trim())
                        if (m.find()) {
                            Log.i("TAG", "regex: ${body}")
                        }*/
                        lstSms.add(
                            SMSModel(
                                address = address,
                                body = body,
                                date = date,
                                avlBalance = removeFirstOccurance(getAvailableBalance(body).toString()),
                                balance = removeFirstOccurance(getAmount(body)),
//                                accNumber = "Acc No: ${getAccountNumber(body)}",
                                cardType = findCreditCardOrDebitCard(body, address),
//                                refNumber = "Ref Number: ${getRefComanNumber(body)}",
                                transactionType = transactionType(body),
                                bankName = bankName[address.substring(3, address.length)
                                    .uppercase()]
                                    ?: address.substring(3, address.length)
                            )
                        )
                    } else {
                        // getSMSForCheckAppInstalled
                        if (isAppNameContains(body)) {
                            competitorAppList.add(
                                SMSAppModel(app_name = address.substring(3, address.length), body)
                            )
                        }
                    }
                    Log.i("TAG", "getSMSList: ${cursor.getString(index_Address)}")
                } while (cursor.moveToNext())
            } else {
                // empty box, no SMS
            }
        } catch (e: Exception) {
            Toast.makeText(this, "${e.message}", Toast.LENGTH_LONG).show()
            e.printStackTrace()
        }
        return lstSms
    }

    fun removeFirstOccurance(amount: String): Double {
        return if (amount.toString()
                .count { ch -> ch == '.' } > 1
        ) {
            amount.toString()
                .replaceFirst(".".toRegex(), "").toDouble()
        } else {
            amount.toDouble()
        }
    }

    companion object {
        val appMap = HashMap<String, String>().also {
            it["AcePro"] = "A23"
            it["ATOTHIR"] = "A23"
            it["RMYCRL"] = "RummyCircle"
            it["RUCULT"] = "RummyCulture"
        }

        val bankName = HashMap<String, String>().also {
            it["ICICIB"] = "ICICI Bank"
            it["HDFCBK"] = "HDFC Bank"
            it["SBIINB"] = "SBI Bank"
            it["SBMSMS"] = "SBI Bank"
            it["SCISMS"] = "SCISMS"
            it["CBSSBI"] = "SBI Bank"
            it["SBIPSG"] = "SBI Bank"
            it["SBIUPI"] = "SBI Bank"
            it["SBICRD"] = "SBI Bank"
            it["ATMSBI"] = "SBI Bank"
            it["QPMYAMEX"] = "QPMYAMEX"
            it["IDFCFB"] = "IDFC Bank"
            it["UCOBNK"] = "UCO Bank"
            it["CANBNK"] = "CANADA Bank"
            it["BOIIND"] = "BOI"
            it["AXISBK"] = "AXIS Bank"
            it["PAYTMB"] = "PAYTM Bank"
            it["PaytMB"] = "PAYTM Bank"
            it["IPAYTM"] = "PAYTM Bank"
            it["VPAYTM"] = "PAYTM Bank"
            it["UnionB"] = "UNION Bank"
            it["UNIONB"] = "UNION Bank"
            it["INDBNK"] = "INDBNK"
            it["KOTAKB"] = "Kotaka Bank"
            it["CENTBK"] = "CENTBK"
            it["SCBANK"] = "SCBANK"
            it["PNBSMS"] = "PNB Bank"
            it["DOPBNK"] = "DOPBNK"
            it["YESBNK"] = "ES Bank"
            it["IDBIBK"] = "IDBI Bank"
            it["ALBANK"] = "ALBANK"
            it["CITIBK"] = "CITI Bank"
            it["ANDBNK"] = "ANDBNK"
            it["BOBTXN"] = "BOBTXN"
            it["IOBCHN"] = "IOBCHN"
            it["MAHABK"] = "MAHARASTRA Bank"
            it["OBCBNK"] = "OBCBNK"
            it["RBLBNK"] = "RBL Bank"
            it["RBLCRD"] = "RBLCRD"
            it["SPRCRD"] = "SPRCRD"
            it["HSBCBK"] = "HSBC Bank"
            it["HSBCIN"] = "HSBC Bank"
            it["DBSBNK"] = "DBS Bank"
            it["SLCEIT"] = "SLICE CARD"
            it["UniCrd"] = "UNI Card"
            it["UNICRD"] = "UNI Card"
            it["INDUSB"] = "INDUSB"
        }
    }
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
    val bankName: String = "",
    val date: Long = 0
)

data class SMSAppModel(
    val app_name: String,
    val body: String
)