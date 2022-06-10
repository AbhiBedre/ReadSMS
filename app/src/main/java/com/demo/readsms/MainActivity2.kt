package com.demo.readsms

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.io.*

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
//        val fileInputStream = FileInputStream(File(this.filesDir, "rules.json"))
//        val b: String = mo17213b(fileInputStream, null as OutputStream?)
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

    /*fun mo17218h(context: Context): LinkedHashMap<String?, AccountMiscInfo?>? {
        var d: AccountMiscInfo
        if (f22730c == null) {
            f22730c =
                LinkedHashMap<String, AccountMiscInfo>()
            try {
                val fileInputStream = FileInputStream(File(context.filesDir, "rules.json"))
                val b: String = mo17213b(fileInputStream, null as OutputStream?)
                fileInputStream.close()
                val rules: Rules = C5582j().mo11658d(b, Rules::class.java) as Rules
                Collections.sort<Any>(rules.rules, C8601c.f22722o)
                val it: Iterator<Sender> = rules.rules.iterator()
                while (it.hasNext()) {
                    val next: Sender = it.next()
                    val str: String = next.name
                    val str2: String = next.completeName
                    val miscInfo: MiscInfo = next.miscInfo
                    if (!(miscInfo == null || mo17215d(miscInfo).also { d = it } == null)) {
                        d.setCompleteName(str2)
                        p013c.p014a.p015a.p016a.p432t2.C8606h.f22730c.put(str, d)
                    }
                }
            } catch (e: IOException) {
                e.printStackTrace()
            } catch (e2: JsonSyntaxException) {
                e2.printStackTrace()
            }
        }
        return p013c.p014a.p015a.p016a.p432t2.C8606h.f22730c
    }

    class AccountMiscInfo {
        var completeName: String? = null
        var getBalanceInfo: List<GetBalanceInfo>? = null

        class BalanceContactInfo {
            var balRefreshText: String? = null
            var contactSmsFormat: String? = null
            var contactType = 0
            var contact: Array<String>

            fun getContactTypeInt(str: String): Int {
                if (str.equals("sms", ignoreCase = true)) {
                    return 0
                }
                return if (str.equals("voice", ignoreCase = true)) 1 else -1
            }

            val primaryContact: String?
                get() {
                    val strArr = contact
                    return if (strArr.size > 0) {
                        strArr[0]
                    } else null
                }

            override fun toString(): String {
                val s0: StringBuilder = C0741a.m1765s0("AccountMiscInfo{contacts='")
                s0.append(contact)
                s0.append('\'')
                s0.append(", contactType=")
                s0.append(contactType)
                s0.append(", contactSmsFormat='")
                s0.append(contactSmsFormat)
                s0.append('\'')
                s0.append('}')
                return s0.toString()
            }

            companion object {
                const val CONTACT_TYPE_SMS = 0
                const val CONTACT_TYPE_VOICE = 1
            }
        }

        class GetBalanceInfo {
            var accountType = 0
            var balContactInfo: List<BalanceContactInfo>? = null
            fun getBalanceContactInfo(i: Int): BalanceContactInfo? {
                val list = balContactInfo ?: return null
                for (next in list) {
                    if (next.contactType == i) {
                        return next
                    }
                }
                return null
            }

            fun setBalContactInfo(arrayList: ArrayList<BalanceContactInfo>?) {
                balContactInfo = arrayList
            }

            override fun toString(): String {
                val s0: StringBuilder = C0741a.m1765s0("AccountMiscInfo{accountType=")
                s0.append(accountType)
                s0.append(", balContactInfo=")
                s0.append(balContactInfo)
                s0.append('}')
                return s0.toString()
            }
        }

        override fun toString(): String {
            val s0: StringBuilder = C0741a.m1765s0("AccountMiscInfo{getBalanceInfo=")
            s0.append(getBalanceInfo)
            s0.append('}')
            return s0.toString()
        }

        fun getGetBalanceInfo(i: Int): GetBalanceInfo? {
            var i = i
            val list = getBalanceInfo ?: return null
            if (i == 1) {
                i = 2
            }
            for (next in list) {
                if (next.accountType == i) {
                    return next
                }
            }
            return null
        }
    }

    @Keep
    class Rules {
        @C5454c("blacklist_regex")
        @C5452a
        var blacklistRegex: String? = null

        @C5454c("min_app_version")
        @C5452a
        var minAppVersion: String? = null

        @C5454c("rules")
        @C5452a
        var rules: java.util.ArrayList<Sender>? = null

        @C5454c("version")
        @C5452a
        var version: String? = null
        override fun toString(): String {
            val s0: java.lang.StringBuilder = C0741a.m1765s0("Rules{blacklistRegex='")
            C0741a.m1725c1(s0, blacklistRegex, '\'', ", minAppVersion='")
            C0741a.m1725c1(s0, minAppVersion, '\'', ", version='")
            C0741a.m1725c1(s0, version, '\'', ", rules=")
            s0.append(rules)
            s0.append('}')
            return s0.toString()
        }
    }

    class Sender {
        var account: Account? = null
        var accountUUID: String? = null
        var cInfo: ContactInfo? = null
        var color = 0
        var count = 0
        var date: Long = 0
        var description: String? = null
        var drawable: Drawable? = null
        var isLocalImageAvailable = false
        var isPersonalSender = false
        var mostRecentSMS: ShortSms? = null
        var resId: Int? = null
        var sender: String? = null
        var senderName: String? = null
        var showStar = false
        var smses: ArrayList<ShortSms>? = null
        var type = 0
        var unreadCnt = 0
        override fun toString(): String {
            val s0: java.lang.StringBuilder = C0741a.m1765s0("Sender{sender='")
            C0741a.m1725c1(s0, sender, '\'', ", senderName='")
            C0741a.m1725c1(s0, senderName, '\'', ", description='")
            C0741a.m1725c1(s0, description, '\'', ", date=")
            s0.append(date)
            s0.append(", count=")
            s0.append(count)
            s0.append(", unreadCnt=")
            s0.append(unreadCnt)
            s0.append(", type=")
            s0.append(type)
            s0.append(", accountUUID='")
            C0741a.m1725c1(s0, accountUUID, '\'', ", resId=")
            s0.append(resId)
            s0.append(", drawable=")
            s0.append(drawable)
            s0.append(", smses=")
            s0.append(smses)
            s0.append(", mostRecentSMS=")
            s0.append(mostRecentSMS)
            s0.append(", color=")
            s0.append(color)
            s0.append(", isLocalImageAvailable=")
            s0.append(isLocalImageAvailable)
            s0.append(", isPersonalSender=")
            s0.append(isPersonalSender)
            s0.append(", showStar=")
            s0.append(showStar)
            s0.append(", account=")
            s0.append(account)
            s0.append(", cInfo=")
            s0.append(cInfo)
            s0.append('}')
            return s0.toString()
        }

        companion object {
            const val TYPE_CUSTOM_HEADER = 3
            const val TYPE_HEADER = 0
            const val TYPE_LOW_PRIORITY = 5
            const val TYPE_SENDER = 4
            const val TYPE_SPAM = 6
            const val TYPE_SPENDS_HEADER = 2
            const val TYPE_TITLE_HEADER = 1
        }
    }*/
}