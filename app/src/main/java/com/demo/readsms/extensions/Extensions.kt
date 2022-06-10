package com.demo.readsms.extensions

import android.annotation.SuppressLint
import android.util.Log
import java.text.DateFormat
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern

val regEx =
    Pattern.compile("(?i)(?:RS|INR|MRP)?(?:(?:RS|INR|MRP)\\.?\\s?)(\\d+(:?\\,\\d+)?(\\,\\d+)?(\\.\\d{1,2})?)+")

@SuppressLint("LongLogTag")
fun getAvailableBalance(smsBody: String): Double {
    // Find instance of pattern matches
    if (smsBody.contains("curr o/s - ", true)) {
        var newBody = smsBody.split("o/s - ")
        if (newBody.isNotEmpty() && newBody.size > 1) {
            val m = regEx.matcher(newBody[1].trim())
            if (m.find()) {
                var amount = m.group(0) ?: "0.0"
                amount = amount.replace("rs.", "", true)
                amount = amount.replace("rs", "", true)
                amount = amount.replace("inr", "", true)
                amount = amount.replace(" ", "", true)
                amount = amount.replace(",", "", true)
                if (amount.count { ch -> ch == '.' } > 1) {
                    amount = amount.replaceFirst(".".toRegex(), "")
                }
                return if (amount.isEmpty()) 0.0 else amount.toDouble()
            }
            // smsDto.refNumber="balance"
        } else {
            return 0.0
        }
    } else if (smsBody.contains("The Balance is", true)) {
        var newBody = smsBody.split("The Balance is ")
        if (newBody.isNotEmpty() && newBody.size > 1) {
            val m = regEx.matcher(newBody[1].trim())
            if (m.find()) {
                var amount = m.group(0) ?: "0.0"
                amount = amount.replace("rs.", "", true)
                amount = amount.replace("rs", "", true)
                amount = amount.replace("inr", "", true)
                amount = amount.replace(" ", "", true)
                amount = amount.replace(",", "", true)
                if (amount.count { ch -> ch == '.' } > 1) {
                    amount = amount.replaceFirst(".".toRegex(), "")
                }
                return if (amount.isEmpty()) 0.0 else amount.toDouble()
            }
            // smsDto.refNumber="balance"
        } else {
            return 0.0
        }
    } else if (smsBody.contains("The Available Balance is", true)) {
        var newBody = smsBody.split("The Available Balance is ")
        if (newBody.isNotEmpty() && newBody.size > 1) {
            val m = regEx.matcher(newBody[1].trim())
            if (m.find()) {
                var amount = m.group(0) ?: "0.0"
                amount = amount.replace("rs.", "", true)
                amount = amount.replace("rs", "", true)
                amount = amount.replace("inr", "", true)
                amount = amount.replace(" ", "", true)
                amount = amount.replace(",", "", true)
                if (amount.count { ch -> ch == '.' } > 1) {
                    amount = amount.replaceFirst(".".toRegex(), "")
                }
                return if (amount.isEmpty()) 0.0 else amount.toDouble()
            }
            // smsDto.refNumber="balance"
        } else {
            return 0.0
        }
//        return readAmountFromAvlBal(m)
    } else if (smsBody.contains("Avbl Lmt:", true)) {
        var newBody = smsBody.split("Avbl Lmt:")
        if (newBody.isNotEmpty() && newBody.size > 1) {
            val m = regEx.matcher(newBody[1].trim())
            if (m.find()) {
                var amount = m.group(0) ?: "0.0"
                amount = amount.replace("rs.", "", true)
                amount = amount.replace("rs", "", true)
                amount = amount.replace("inr", "", true)
                amount = amount.replace(" ", "", true)
                amount = amount.replace(",", "", true)
                if (amount.count { ch -> ch == '.' } > 1) {
                    amount = amount.replaceFirst(".".toRegex(), "")
                }
                return if (amount.isEmpty()) 0.0 else amount.toDouble()
            }
            // smsDto.refNumber="balance"
        } else {
            return 0.0
        }
    } else if (smsBody.contains("Avlbal", true)) {
        var newBody = smsBody.split("Avlbal")
        if (newBody.isNotEmpty() && newBody.size > 1) {
            val m = regEx.matcher(newBody[1].trim())
            if (m.find()) {
                var amount = m.group(0) ?: "0.0"
                amount = amount.replace("rs.", "", true)
                amount = amount.replace("rs", "", true)
                amount = amount.replace("inr", "", true)
                amount = amount.replace(" ", "", true)
                amount = amount.replace(",", "", true)
                if (amount.count { ch -> ch == '.' } > 1) {
                    amount = amount.replaceFirst(".".toRegex(), "")
                }
                return if (amount.isEmpty()) 0.0 else amount.toDouble()
            }
            // smsDto.refNumber="balance"
        } else {
            return 0.0
        }
    } else if (smsBody.contains("balance is", true)) {
        var newBody = smsBody.toLowerCase().split("balance is ")
        if (newBody.isNotEmpty() && newBody.size > 1) {
            val m = regEx.matcher(newBody[1].trim())
            if (m.find()) {
                var amount = m.group(0) ?: "0.0"
                amount = amount.replace("rs.", "", true)
                amount = amount.replace("rs", "", true)
                amount = amount.replace("inr", "", true)
                amount = amount.replace(" ", "", true)
                amount = amount.replace(",", "", true)
                if (amount.count { ch -> ch == '.' } > 1) {
                    amount = amount.replaceFirst(".".toRegex(), "")
                }
                return if (amount.isEmpty()) 0.0 else amount.toDouble()
            }
            // smsDto.refNumber="balance"
        } else {
            return 0.0
        }
    } else if (smsBody.contains("AvBl Bal:", true)) {
        var newBody = smsBody.toLowerCase().split("avbl bal: ")
        if (newBody.isNotEmpty() && newBody.size > 1) {
            val m = regEx.matcher(newBody[1].trim())
            if (m.find()) {
                var amount = m.group(0) ?: "0.0"
                amount = amount.replace("rs.", "", true)
                amount = amount.replace("rs", "", true)
                amount = amount.replace("inr", "", true)
                amount = amount.replace(" ", "", true)
                amount = amount.replace(",", "", true)
                if (amount.count { ch -> ch == '.' } > 1) {
                    amount = amount.replaceFirst(".".toRegex(), "")
                }
                return if (amount.isEmpty()) 0.0 else amount.toDouble()
            }
            // smsDto.refNumber="balance"
        } else {
            return 0.0
        }
    } else if (smsBody.contains("Avl. Bal:", true)) {
        var newBody = smsBody.toLowerCase().split("avl. bal:")
        if (newBody.isNotEmpty() && newBody.size > 1) {
            val m = regEx.matcher(newBody[1].trim())
            if (m.find()) {
                var amount = m.group(0) ?: "0.0"
                amount = amount.replace("rs.", "", true)
                amount = amount.replace("rs", "", true)
                amount = amount.replace("inr", "", true)
                amount = amount.replace(" ", "", true)
                amount = amount.replace(",", "", true)
                if (amount.count { ch -> ch == '.' } > 1) {
                    amount = amount.replaceFirst(".".toRegex(), "")
                }
                return if (amount.isEmpty()) 0.0 else amount.toDouble()
            }
            // smsDto.refNumber="balance"
        } else {
            return 0.0
        }
    } else if (smsBody.contains("AVl BAL", true)) {
        if (smsBody.contains("Avl. Bal:", true)) {
            var newBody = smsBody.toLowerCase().split("avl. bal:")
            if (newBody.isNotEmpty() && newBody.size > 1) {
                val m = regEx.matcher(newBody[1].trim())
                if (m.find()) {
                    var amount = m.group(0) ?: "0.0"
                    amount = amount.replace("rs.", "", true)
                    amount = amount.replace("rs", "", true)
                    amount = amount.replace("inr", "", true)
                    amount = amount.replace(" ", "", true)
                    amount = amount.replace(",", "", true)
                    if (amount.count { ch -> ch == '.' } > 1) {
                        amount = amount.replaceFirst(".".toRegex(), "")
                    }
                    return if (amount.isEmpty()) 0.0 else amount.toDouble()
                }
                // smsDto.refNumber="balance"
            } else {
                return 0.0
            }
        } else {
            var newBody = smsBody.toLowerCase().split("avl bal ")
            if (newBody.isNotEmpty() && newBody.size > 1) {
                val m = regEx.matcher(newBody[1].trim())
                if (m.find()) {
                    var amount = m.group(0) ?: "0.0"
                    amount = amount.replace("rs.", "", true)
                    amount = amount.replace("rs", "", true)
                    amount = amount.replace("inr", "", true)
                    amount = amount.replace(" ", "", true)
                    amount = amount.replace(",", "", true)
                    if (amount.count { ch -> ch == '.' } > 1) {
                        amount = amount.replaceFirst(".".toRegex(), "")
                    }
                    return if (amount.isEmpty()) 0.0 else amount.toDouble()
                }
                // smsDto.refNumber="balance"
            } else {
                return 0.0
            }
        }
    } else if (smsBody.contains("Avail Bal", true)) {
        var newBody = smsBody.toLowerCase().split("avail bal ")
        if (newBody.isNotEmpty() && newBody.size > 1) {
            val m = regEx.matcher(newBody[1].trim().replace("\\s".toRegex(), ""))
            if (m.find()) {
                var amount = m.group(0) ?: "0.0"
                amount = amount.replace("rs.", "", true)
                amount = amount.replace("rs", "", true)
                amount = amount.replace("inr", "", true)
                amount = amount.replace(" ", "", true)
                amount = amount.replace(",", "", true)
                if (amount.count { ch -> ch == '.' } > 1) {
                    amount = amount.replaceFirst(".".toRegex(), "")
                }
                return if (amount.isEmpty()) 0.0 else amount.toDouble()
            }
            // smsDto.refNumber="balance"
        } else {
            return 0.0
        }
    } else if (smsBody.contains("The combine BAL is", true)) {
        var newBody = smsBody.toLowerCase().split("bal is ")
        if (newBody.isNotEmpty() && newBody.size > 1) {
            val m = regEx.matcher(newBody[1].trim())
            if (m.find()) {
                var amount = m.group(0) ?: "0.0"
                amount = amount.replace("rs.", "", true)
                amount = amount.replace("rs", "", true)
                amount = amount.replace("inr", "", true)
                amount = amount.replace(" ", "", true)
                amount = amount.replace(",", "", true)
                if (amount.count { ch -> ch == '.' } > 1) {
                    amount = amount.replaceFirst(".".toRegex(), "")
                }
                return if (amount.isEmpty()) 0.0 else amount.toDouble()
            }
            // smsDto.refNumber="balance"
        } else {
            return 0.0
        }
    } else if (smsBody.contains("The balance in", true)) {
        var newBody = smsBody.toLowerCase().split("balance in ")
        if (newBody.isNotEmpty() && newBody.size > 1) {
            val m = regEx.matcher(newBody[1].trim())
            if (m.find()) {
                var amount = m.group(0) ?: "0.0"
                amount = amount.replace("rs.", "", true)
                amount = amount.replace("rs", "", true)
                amount = amount.replace("inr", "", true)
                amount = amount.replace(" ", "", true)
                amount = amount.replace(",", "", true)
                if (amount.count { ch -> ch == '.' } > 1) {
                    amount = amount.replaceFirst(".".toRegex(), "")
                }
                return if (amount.isEmpty()) 0.0 else amount.toDouble()
            }
            // smsDto.refNumber="balance"
        } else {
            return 0.0
        }
    } else if (smsBody.contains("Available balance:", true)) {
        var newBody = smsBody.toLowerCase().split("available balance:")
        if (newBody.isNotEmpty() && newBody.size > 1) {
            val m = regEx.matcher(newBody[1].trim())
            if (m.find()) {
                var amount = m.group(0) ?: "0.0"
                amount = amount.replace("rs.", "", true)
                amount = amount.replace("rs", "", true)
                amount = amount.replace("inr", "", true)
                amount = amount.replace(" ", "", true)
                amount = amount.replace(",", "", true)
                if (amount.count { ch -> ch == '.' } > 1) {
                    amount = amount.replaceFirst(".".toRegex(), "")
                }
                return if (amount.isEmpty()) 0.0 else amount.toDouble()
            }
            // smsDto.refNumber="balance"
        } else {
            return 0.0
        }
    } else if (smsBody.contains("Current balance is", true)) {
        var newBody = smsBody.toLowerCase().split("Current balance is")
        if (newBody.isNotEmpty() && newBody.size > 1) {
            val m = regEx.matcher(newBody[1].trim())
            if (m.find()) {
                var amount = m.group(0) ?: "0.0"
                amount = amount.replace("rs.", "", true)
                amount = amount.replace("rs", "", true)
                amount = amount.replace("inr", "", true)
                amount = amount.replace(" ", "", true)
                amount = amount.replace(",", "", true)
                if (amount.count { ch -> ch == '.' } > 1) {
                    amount = amount.replaceFirst(".".toRegex(), "")
                }
                return if (amount.isEmpty()) 0.0 else amount.toDouble()
            }
            // smsDto.refNumber="balance"
        } else {
            return 0.0
        }
    } else {
        return 0.0
    }
    return 0.0
}

fun readAmountFromAvlBal(m: Matcher): Double {
    return if (m.find()) {
        var amount = m.group(0).replace("inr".toRegex(), "")
        amount = amount.replace("rs.", "", true)
        amount = amount.replace("rs".toRegex(), "")
        amount = amount.replace("inr".toRegex(), "")
        amount = amount.replace(" ".toRegex(), "")
        amount = amount.replace(",".toRegex(), "")
        if (amount.count { ch -> ch == '.' } > 1) {
            amount = amount.replaceFirst(".".toRegex(), "")
        }
        if (amount.isEmpty()) 0.0 else amount.toDouble()
    } else {
        0.0
    }
}

fun transactionType(smsBody: String): String {
    return if (smsBody.contains("withdrawn", true)
        || smsBody.contains("debited", true)
        || smsBody.contains("spent", true)
        || smsBody.contains("paying", true)
        || smsBody.contains("payment", true)
        || smsBody.contains("deducted", true)
        || smsBody.contains("debited", true)
        || smsBody.contains("purchase", true)
        || smsBody.contains("dr", true)
        && !smsBody.contains("otp", true)
        || smsBody.contains("txn", true)
        || smsBody.contains("transfer", true)
        && !smsBody.contains("We are pleased to inform that", true)
        && !smsBody.contains("has been opened", true)
    ) {
        "debited"
    } else if (smsBody.contains("credited", true)
        || smsBody.contains("cr", true)
        || smsBody.contains("deposited", true)
        || smsBody.contains("deposit", true)
        || smsBody.contains("received", true)
        && !smsBody.contains("otp", true)
        && !smsBody.contains("emi", true)
    ) {
        when {
            smsBody.contains("UPDATE:AVAILABLE Bal in", true) -> {
                "balance"
            }
            smsBody.contains("UPDATE: AVAILABLE Bal in", true) -> {
                "balance"
            }
            else -> {
                "credited"
            }
        }
    } else {
        ""
    }
}

fun findCreditCardOrDebitCard(msg: String, sender: String): String {
    if (checkSenderIsValid(sender = sender)) {
        return if (msg.contains("CREDIT CARD", ignoreCase = true) ||
            msg.contains("SBICARD", ignoreCase = true)
        ) {
            "credit card"
        } else {
            "debit card"
        }
    }
    return "N/A"
}

// getting amount by matching the pattern
fun getAmount(data: String?): String {
    // pattern - rs. **,***.**
    val pattern1 = "(?i)(?:(?:RS|INR|MRP)\\.?\\s?)(\\d+(:?\\,\\d+)?(\\,\\d+)?(\\.\\d{1,2})?)"
    val regex1: Pattern = Pattern.compile(pattern1)
    val matcher1: Matcher = regex1.matcher(data)
    if (matcher1.find()) {
        try {
            var a: String = matcher1.group(0).lowercase()
            a = a.replace("inr", "")
            a = a.replace("rs.", "")
            a = a.replace("rs", "")
            a = a.replace(" ", "")
            a = a.replace(",", "")
            return a
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    return "0.0"
}

fun getRefComanNumber(body: String): String {
    //Info, At, Linked to, NEFT, Ref, transfer from, transfer to, for, of, IMPS
    //Till dot Space, on, has
    var refNumber = ""
    if (body.contains("NetBanking", true)) {
        // refNumber = " NetBanking"
        if (body.toLowerCase().contains(" to ", true)) {
            var dataList = body.toLowerCase().split(" to ")
            var data = ""
            data = if (dataList.size > 1) {
                dataList[1]
            } else {
                dataList[0]
            }
            refNumber = when {
                data.contains(". ", true) -> {
                    data.split(". ")[0]
                }
                data.contains(" on ", true) -> {
                    data.split(" on ")[0]
                }
                data.contains(")", true) -> {
                    data.split(")")[0]
                }

                else -> {
                    //data
                    "NetBanking"
                }
            }
        } else if (body.toLowerCase().contains(" for ", true)) {
            var dataList = body.toLowerCase().split(" for ")
            var data = ""
            data = if (dataList.size > 1) {
                dataList[1]
            } else {
                dataList[0]
            }
            refNumber = when {
                data.contains(". ", true) -> {
                    data.split(". ")[0]
                }
                data.contains(" on ", true) -> {
                    data.split(" on ")[0]
                }
                data.contains(")", true) -> {
                    data.split(")")[0]
                }

                else -> {
                    //data
                    "NetBanking"
                }
            }
        }

    } else if (body.contains("Cash Deposit", true)) {
        refNumber = "Cash Deposit"
    } else if (body.contains("withdrawn", true)) {
        var dataList = body.toLowerCase().split(" at ")
        var data = ""
        data = if (dataList.size > 1) {
            dataList[1]
        } else {
            dataList[0]
        }
        refNumber = "withdrawn at " + when {
            data.contains("on", true) -> {
                data.split(" on")[0]
            }
            data.contains(".", true) -> {
                data.split(". ")[0]
            }
            data.contains(")", true) -> {
                data.split(")")[0]
            }
            else -> {
                data
            }
        }
    } else if (body.contains("towards", true)) {
        var dataList = body.toLowerCase().split("towards ")
        var data = ""
        data = if (dataList.size > 1) {
            dataList[1]
        } else {
            dataList[0]
        }
        refNumber = when {
            data.contains(" avl ", true) -> {
                data.split(" avl ")[0]
            }
            data.contains(". ", true) -> {
                data.split(". ")[0]
            }
            data.contains("on", true) -> {
                data.split(" on")[0]
            }
            data.contains(")", true) -> {
                data.split(")")[0]
            }
            else -> {
                data
            }
        }

    } else if (body.contains("thru", true)) {
        if (!body.contains("thru clg", true)) {
            var dataList = body.toLowerCase().split("thru ")
            var data = ""
            data = if (dataList.size > 1) {
                dataList[1]
            } else {
                dataList[0]
            }
            refNumber = when {
                data.contains(". ", true) -> {
                    data.split(".")[0]
                }
                data.contains("on", true) -> {
                    data.split(" on")[0]
                }
                data.contains(")", true) -> {
                    data.split(")")[0]
                }
                else -> {
                    data
                }
            }
        }

    } else if (body.contains("Credit card ending", true)) {
        if (body.contains("has been", true) && body.contains("from", true)) {
            var dataList = body.toLowerCase().split("from ")
            var data = ""
            data = if (dataList.size > 1) {
                dataList[1]
            } else {
                dataList[0]
            }
            refNumber = when {
                data.contains(" on", true) -> {
                    data.split(" on")[0]
                }
                data.contains(". ", true) -> {
                    data.split(". ")[0]
                }
                data.contains(")", true) -> {
                    data.split(")")[0]
                }
                else -> {
                    data
                }
            }
        } else if (body.contains("has been", true)) {
            var dataList = body.toLowerCase().split("has been ")
            var data = ""
            data = if (dataList.size > 1) {
                dataList[1]
            } else {
                dataList[0]
            }
            refNumber = when {
                data.contains("on", true) -> {
                    data.split(" on")[0]
                }
                data.contains(")", true) -> {
                    data.split(")")[0]
                }
                data.contains(". ", true) -> {
                    data.split(".")[0]
                }
                else -> {
                    data
                }
            }
        } else {
            var dataList = body.toLowerCase().split("from ")
            var data = ""
            data = if (dataList.size > 1) {
                dataList[1]
            } else {
                dataList[0]
            }
            refNumber = when {
                data.contains("on", true) -> {
                    data.split(" on")[0]
                }
                data.contains(")", true) -> {
                    data.split(")")[0]
                }
                data.contains(". ", true) -> {
                    data.split(".")[0]
                }
                else -> {
                    data
                }
            }
        }

    } else if (body.contains("NEFT", true)) {
        var dataList = body.toLowerCase().split("neft")
        //val p1 = Pattern.compile("([0-9]+).*")
        var data = ""
        if (dataList.size > 1) {
            data = dataList[1].trim()
        } else {
            data = dataList[0].trim()
        }
        refNumber = "NEFT " + when {
            data.contains(")", true) -> {
                data.split(")")[0]
            }
            data.contains(".", true) -> {
                data.split(". ")[0]
            }
            data.contains("-", true) -> {
                data.split("-")[0]
            }
            else -> {
                data
            }
        }
    } else if (body.contains("IMPS", true)) {
        if (body.contains("Ref no")) {
            var dataList = body.split("Ref no")
            val p1 = Pattern.compile("([0-9]+).*")
            var data = ""
            if (dataList.size > 1) {
                val m1 = p1.matcher(dataList[1])
                while (m1.find()) {
                    data = m1.group()
                    break
                }
            } else {
                val m1 = p1.matcher(dataList[0])
                while (m1.find()) {
                    data = m1.group()
                    break
                }
            }
            when {
                data.contains(")", true) -> {
                    refNumber = "IMPS Ref no" + data.split(")")[0]
                }
                data.contains(".", true) -> {
                    refNumber = "IMPS Ref no" + data.split(".")[0]
                }
                else -> {
                    refNumber = data
                }
            }
        } else {
            var dataList = body.split("IMPS")
            val p1 = Pattern.compile("([0-9]+).*")
            var data = ""
            if (dataList.size > 1) {
                val m1 = p1.matcher(dataList[1])
                while (m1.find()) {
                    data = m1.group()
                    break
                }
            } else {
                val m1 = p1.matcher(dataList[0])
                while (m1.find()) {
                    data = m1.group()
                    break
                }
            }
            when {
                data.contains(")", true) -> {
                    refNumber = "IMPS " + data.split(")")[0]
                }
                data.contains(".", true) -> {
                    refNumber = "IMPS " + data.split(". ")[0]
                }
                else -> {
                    refNumber = data
                }
            }
        }


    } else if (body.contains("RefNo", true)) {
        var dataList = body.split("RefNo")
        val p1 = Pattern.compile("([0-9]+).*")
        var data = ""
        if (dataList.size == 2) {
            val m1 = p1.matcher(dataList[1])
            while (m1.find()) {
                data = m1.group()
                break
            }
        } else {
            val m1 = p1.matcher(dataList[0])
            while (m1.find()) {
                data = m1.group()
                break
            }
        }
        when {
            data.contains(")", true) -> {
                refNumber = "RefNo " + data.split(")")[0]
            }
            data.contains(".", true) -> {
                refNumber = "RefNo " + data.split(". ")[0]
            }
            data.contains("on", true) -> {
                refNumber = "RefNo " + data.toLowerCase().split(" on")[0]
            }
            data.contains("has", true) -> {
                refNumber = "RefNo " + data.toLowerCase().split(" has")[0]
            }
            else -> {
                refNumber = data
            }
        }

    } else if (body.contains("Ref no", true)) {
        if (body.contains("VPA", true)) {
            var dataList = body.toLowerCase().split("vpa ")
            var data = ""
            data = if (dataList.size > 1) {
                dataList[1]
            } else {
                dataList[0]
            }
            when {
                data.contains(".", true) -> {
                    refNumber = "VPA " + data.split(". ")[0]
                }
                data.contains(")", true) -> {
                    refNumber = "VPA " + data.split(")")[0]
                }
                data.contains("on", true) -> {
                    refNumber = "VPA " + data.split(" on")[0]
                }
                data.contains("has", true) -> {
                    refNumber = "VPA " + data.split(" has")[0]
                }
                else -> {
                    refNumber = data
                }
            }
        } else {
            var dataList = body.toLowerCase().split("ref no")
            var data = ""
            data = if (dataList.size > 1) {
                dataList[1]
            } else {
                dataList[0]
            }
            when {
                data.contains(")", true) -> {
                    refNumber = "Ref no" + data.split(")")[0]
                }
                data.contains(".", true) -> {
                    refNumber = "Ref no" + data.split(". ")[0]
                }
                data.contains("on", true) -> {
                    refNumber = "Ref no" + data.split(" on")[0]
                }
                data.contains("has", true) -> {
                    refNumber = "Ref no" + data.split(" has")[0]
                }
                else -> {
                    refNumber = data
                }
            }
        }


    } else if (body.contains("Ref#", true)) {
        var dataList = body.split("Ref#")
        //val p1 = Pattern.compile("([0-9]+).*")
        var data = ""
        if (dataList.size == 2) {
            data = dataList[1]
        } else {
            data = dataList[0]
        }
        when {
            data.decapitalize().contains("on", true) -> {
                refNumber = "Ref no" + data.split(" on")[0]
            }
            data.decapitalize().contains("has", true) -> {
                refNumber = "Ref no" + data.split(" has")[0]
            }
            data.contains(")", true) -> {
                refNumber = "Ref no" + data.split(")")[0]
            }
            data.contains(".", true) -> {
                refNumber = "Ref no" + data.split(".")[0]
            }
            else -> {
                refNumber = data
            }
        }
    } else if (body.contains("Info", true)) {
        var dataList = body.split("Info")
        //val p1 = Pattern.compile("([0-9]+).*")
        var data = ""
        if (dataList.size == 2) {
            data = dataList[1]
        } else {
            data = dataList[0]
        }
        when {
            data.contains(")", true) -> {
                refNumber = data.split(")")[0]
            }
            data.contains(".", true) -> {
                refNumber = data.split(".")[0]
            }
            else -> {
                refNumber = data
            }
        }
    } else if (body.contains("Received", true)) {
        if (body.contains("via", true)) {
            var dataList = body.split("via")
            //val p1 = Pattern.compile("([0-9]+).*")
            var data = ""
            data = if (dataList.size > 1) {
                dataList[1]
            } else {
                dataList[0]
            }
            refNumber = "VIA " + when {
                data.decapitalize().contains("on", true) -> {
                    data.split(" on")[0]
                }
                data.contains(")", true) -> {
                    data.split(")")[0]
                }
                data.contains(". ", true) -> {
                    data.split(".")[0]
                }
                else -> {
                    data
                }
            }

        } else if (body.contains("has been", true)) {
            var dataList = body.split("has ")
            //val p1 = Pattern.compile("([0-9]+).*")
            var data = ""
            data = if (dataList.size > 1) {
                dataList[2]
            } else {
                dataList[0]
            }
            refNumber = when {
                data.decapitalize().contains(" on", true) -> {
                    data.split("on")[0]
                }
                data.contains(")", true) -> {
                    data.split(")")[0]
                }
                data.contains(". ", true) -> {
                    data.split(".")[0]
                }
                else -> {
                    data
                }
            }
        }

    } else if (body.contains("ATM", true)) {
        if (body.contains("txn#", true)) {
            var dataList = body.split("ATM")
            //val p1 = Pattern.compile("([0-9]+).*")
            var data = ""
            if (dataList.size > 1) {
                if (dataList.size > 2) {
                    data = dataList[2]
                } else {
                    data = dataList[1]
                }
            } else {
                data = dataList[0]
            }
            when {
                data.decapitalize().contains("fm", true) -> {
                    refNumber = data.split("fm")[0]
                }
                data.decapitalize().contains("has", true) -> {
                    refNumber = data.split(" has")[0]
                }
                data.contains(")", true) -> {
                    refNumber = data.split(")")[0]
                }
                data.contains(".", true) -> {
                    refNumber = data.split(". ")[0]
                }
                else -> {
                    refNumber = data
                }
            }
        } else if (body.contains("tx", true)) {
            var dataList = body.split("tx#")
            //val p1 = Pattern.compile("([0-9]+).*")
            var data = ""
            data = if (dataList.size > 1) {
                dataList[1]
            } else {
                dataList[0]
            }
            when {
                data.contains("fm ", true) -> {
                    refNumber = "ATM " + data.split("fm ")[0]
                }
                data.contains("for", true) -> {
                    refNumber = "ATM " + data.split("for ")[0]
                }
                data.contains(")", true) -> {
                    refNumber = "ATM " + data.split(")")[0]
                }
                data.contains(".", true) -> {
                    refNumber = "ATM " + data.split(". ")[0]
                }
                data.contains("has", true) -> {
                    refNumber = "ATM " + data.split(" has")[0]
                }
                else -> {
                    refNumber = data
                }
            }
        } else if (body.contains("withdrawn", true)) {
            var dataList = body.toLowerCase().split("at ")
            //val p1 = Pattern.compile("([0-9]+).*")
            var data = ""
            if (dataList.size > 1) {
                if (dataList.size > 2) {
                    data = dataList[2]
                } else {
                    data = dataList[1]
                }
            } else {
                data = dataList[0]
            }
            when {
                data.toLowerCase().contains("on", true) -> {
                    refNumber = data.split(" on")[0]
                }
                data.toLowerCase().contains("has", true) -> {
                    refNumber = data.split(" has")[0]
                }
                data.contains(")", true) -> {
                    refNumber = data.split(")")[0]
                }
                data.contains(".", true) -> {
                    refNumber = data.split(". ")[0]
                }
                else -> {
                    refNumber = "ATM " + data
                }
            }
        } else if (body.contains("tx", true)) {
            var dataList = body.split("tx#")
            //val p1 = Pattern.compile("([0-9]+).*")
            var data = ""
            data = if (dataList.size > 1) {
                dataList[1]
            } else {
                dataList[0]
            }
            when {
                data.contains("fm ", true) -> {
                    refNumber = "ATM " + data.split("fm ")[0]
                }
                data.contains("for", true) -> {
                    refNumber = "ATM " + data.split("for ")[0]
                }
                data.contains(")", true) -> {
                    refNumber = "ATM " + data.split(")")[0]
                }
                data.contains(".", true) -> {
                    refNumber = "ATM " + data.split(". ")[0]
                }
                data.contains("has", true) -> {
                    refNumber = "ATM " + data.split(" has")[0]
                }
                else -> {
                    refNumber = data
                }
            }
        } else if (body.contains("has been", true)) {
            var dataList = body.toLowerCase().split("by")
            var data = ""
            data = if (dataList.size > 1) {
                dataList[1].trim()
            } else {
                dataList[0]
            }
            refNumber = when {
                data.contains(" on", true) -> {
                    data.split(" on")[0]
                }
                data.contains(". ", true) -> {
                    data.split(". ")[0]
                }
                data.contains(")", true) -> {
                    data.split(")")[0]
                }
                else -> {
                    data
                }
            }
        }

    } else if (body.contains("by transfer", true)) {
        if (body.contains("Deposit by", true)) {
            var dataList = body.toLowerCase().split("deposit by ")
            //val p1 = Pattern.compile("([0-9]+).*")
            var data = ""
            if (dataList.size > 1) {
                data = dataList[1]
            } else {
                data = dataList[0]
            }
            refNumber = when {
                data.contains(" avl ", true) -> {
                    data.split(" avl ")[0]
                }
                data.contains(".", true) -> {
                    data.split(".")[0]
                }
                data.contains("-", true) -> {
                    data.split("-")[0]
                }
                data.contains(")", true) -> {
                    data.split(")")[0]
                }
                else -> {
                    data
                }
            }
        } else {
            refNumber = "Transfer"
        }
    } else if (body.contains("for UPI", true)) {
        var dataList = body.toLowerCase().split("upi-")
        //val p1 = Pattern.compile("([0-9]+).*")
        var data = ""
        if (dataList.size == 2) {
            data = dataList[1]
        } else {
            data = dataList[0]
        }
        when {
            data.contains(")", true) -> {
                refNumber = data.split(")")[0]
            }
            data.contains(".", true) -> {
                refNumber = data.split(". ")[0]
            }
            else -> {
                refNumber = data
            }
        }
    } else if (body.contains("Credit Card", true)) {
        if (body.contains("Credit card ending", true)) {
            var dataList = body.toLowerCase().split("from ")
            //val p1 = Pattern.compile("([0-9]+).*")
            var data = ""
            if (dataList.size > 1) {
                data = dataList[1].trim()
            } else {
                data = dataList[0]
            }
            when {
                data.contains("on", true) -> {
                    refNumber = getFirstWord(data.split(" on")[0])
                }
                data.contains(")", true) -> {
                    refNumber = getFirstWord(data.split(")")[0])
                }
                data.contains(".", true) -> {
                    refNumber = getFirstWord(data.split(". ")[0])
                }
                else -> {
                    refNumber = getFirstWord(data)
                }
            }

        } else if (body.contains("form", true)) {
            var dataList = body.toLowerCase().split("from ")
            //val p1 = Pattern.compile("([0-9]+).*")
            var data = ""
            if (dataList.size == 2) {
                data = dataList[1]
            } else {
                data = dataList[0]
            }
            when {
                data.contains("on", true) -> {
                    refNumber = data.split(" on")[0]
                }
                data.contains(")", true) -> {
                    refNumber = data.split(")")[0]
                }
                data.contains(".", true) -> {
                    refNumber = data.split(". ")[0]
                }
                else -> {
                    refNumber = data
                }
            }
        } else if (body.contains("spent", true)) {
            var dataList = body.toLowerCase().split("at ")
            //val p1 = Pattern.compile("([0-9]+).*")
            var data = ""
            data = if (dataList.size > 1) {
                dataList[1]
            } else {
                dataList[0]
            }
            when {
                data.contains(" on ", true) -> {
                    refNumber = data.split(" on ")[0]
                }
                data.contains(")", true) -> {
                    refNumber = data.split(")")[0]
                }
                data.contains(".", true) -> {
                    refNumber = data.split(".")[0]
                }
                else -> {
                    refNumber = data
                }
            }
        } else {
            var dataList = body.toLowerCase().split("at")
            //val p1 = Pattern.compile("([0-9]+).*")
            var data = ""
            data = if (dataList.size > 1) {
                dataList[1]
            } else {
                dataList[0]
            }
            when {
                data.contains(" on ", true) -> {
                    refNumber = data.split(" on ")[0]
                }
                data.contains(")", true) -> {
                    refNumber = data.split(")")[0]
                }
                data.contains(".", true) -> {
                    refNumber = data.split(". ")[0]
                }
                else -> {
                    refNumber = data
                }
            }
        }

    } else if (body.contains("payment", true) && !body.contains("spent", true)) {
        var dataList = body.toLowerCase().split("for")
        //val p1 = Pattern.compile("([0-9]+).*")
        var data = ""
        data = if (dataList.size > 1) {
            dataList[1]
        } else {
            dataList[0]
        }
        refNumber = when {
            data.contains("-", true) -> {
                data.split("-")[0]
            }
            data.contains(")", true) -> {
                data.split(")")[0]
            }
            data.contains(".", true) -> {
                data.split(".")[0]
            }
            else -> {
                data
            }
        }
    } else if (body.contains("spent", true)) {
        var dataList = body.toLowerCase().split(" at ")
        //val p1 = Pattern.compile("([0-9]+).*")
        var data = ""
        data = if (dataList.size > 1) {
            dataList[1].trim()
        } else {
            dataList[0]
        }
        when {
            data.contains(" on ", true) -> {
                refNumber = data.split(" on ")[0]
            }
            data.contains(".", true) -> {
                refNumber = data.split(". ")[0]
            }
            data.contains(")", true) -> {
                refNumber = data.split(")")[0]
            }
            else -> {
                refNumber = data
            }
        }
    } else if (body.contains("cheque Number", true)
        || body.contains("cheque No", true)
    ) {
        if (body.contains("cheque No", true)) {
            var dataList = body.toLowerCase().split("cheque no ")
            //val p1 = Pattern.compile("([0-9]+).*")
            var data = ""
            if (dataList.size == 2) {
                data = dataList[1]
            } else {
                data = dataList[0]
            }
            var temp = when {
                data.contains(".", true) -> {
                    data.split(".")[0]
                }
                data.contains("-", true) -> {
                    data.split("-")[0]
                }
                data.contains(")", true) -> {
                    data.split(")")[0]
                }
                else -> {
                    data
                }
            }
            refNumber = "Cheque No " + getFirstWord(temp.trim())
        } else if (body.contains("cheque Number", true)) {
            var dataList = body.toLowerCase().split("cheque number ")
            //val p1 = Pattern.compile("([0-9]+).*")
            var data = ""
            if (dataList.size == 2) {
                data = dataList[1]
            } else {
                data = dataList[0]
            }
            when {
                data.contains(".", true) -> {
                    refNumber = data.split(".")[0]
                }
                data.contains("-", true) -> {
                    refNumber = data.split("-")[0]
                }
                data.contains(")", true) -> {
                    refNumber = data.split(")")[0]
                }
                else -> {
                    refNumber = data
                }
            }
        }


    } else if (body.contains("credit for", true)) {
        var dataList = body.toLowerCase().split("credit for ")
        //val p1 = Pattern.compile("([0-9]+).*")
        var data = ""
        if (dataList.size == 2) {
            data = dataList[1]
        } else {
            data = dataList[0]
        }
        refNumber = "Credit " + when {
            data.contains(" of ", true) -> {
                data.split(" of ")[0]
            }
            data.contains(".", true) -> {
                data.split(".")[0]
            }
            data.contains("-", true) -> {
                data.split("-")[0]
            }
            data.contains(")", true) -> {
                data.split(")")[0]
            }
            else -> {
                data
            }
        }
    } else if (body.contains("Deposit by ", true)) {
        var dataList = body.toLowerCase().split("Deposit by ")
        //val p1 = Pattern.compile("([0-9]+).*")
        var data = ""
        if (dataList.size > 1) {
            data = dataList[1].trim()
        } else {
            data = dataList[0]
        }
        refNumber = when {
            data.contains(" avl ", true) -> {
                data.split(" of ")[0]
            }
            data.contains(".", true) -> {
                data.split(".")[0]
            }
            data.contains("-", true) -> {
                data.split("-")[0]
            }
            data.contains(")", true) -> {
                data.split(")")[0]
            }
            else -> {
                data
            }
        }
    } else if (body.contains("ref", true)) {
        var dataList = body.toLowerCase().split("ref")
        //val p1 = Pattern.compile("([0-9]+).*")
        var data = ""
        data = if (dataList.size > 1) {
            dataList[1].trim()
        } else {
            dataList[0]
        }

        refNumber = "Ref " + getFirstWord(data)
    } else if (body.contains("cheque of", true)) {
        var dataList = body.toLowerCase().split("cheque of ")

        refNumber = "Cheque"
    } else if (body.contains("UPI", true)) {
        var dataList = body.toLowerCase().split("upi")
        //val p1 = Pattern.compile("([0-9]+).*")
        var data = ""
        if (dataList.size > 1) {
            data = dataList[1].trim()
        } else {
            data = dataList[0]
        }
        refNumber = "UPI" + when {
            data.contains(".", true) -> {
                data.split(".")[0]
            }
            data.contains("-", true) -> {
                data.split("-")[0]
            }
            data.contains(")", true) -> {
                data.split(")")[0]
            }
            else -> {
                data
            }
        }
    } else if (body.contains("Credited", true)) {
        var dataList = body.toLowerCase().split(" account of ")
        //val p1 = Pattern.compile("([0-9]+).*")
        var data = ""
        if (dataList.size > 1) {
            data = dataList[1].trim()
        } else {
            data = dataList[0]
        }
        refNumber = "Credited:" + when {
            data.contains("a/c", true) -> {
                data.split("a/c")[0]
            }
            data.contains(".", true) -> {
                data.split(".")[0]
            }
            data.contains("-", true) -> {
                data.split("-")[0]
            }
            data.contains(")", true) -> {
                data.split(")")[0]
            }
            else -> {
                data
            }
        }
    } else if (body.contains("deducted", true)) {
        var dataList = body.toLowerCase().split(" for ")
        //val p1 = Pattern.compile("([0-9]+).*")
        var data = ""
        if (dataList.size > 1) {
            data = dataList[1].trim()
        } else {
            data = dataList[0]
        }
        refNumber = "Credited:" + when {
            data.contains("a/c", true) -> {
                data.split("a/c")[0]
            }
            data.contains(".", true) -> {
                data.split(".")[0]
            }
            data.contains("-", true) -> {
                data.split("-")[0]
            }
            data.contains(")", true) -> {
                data.split(")")[0]
            }
            else -> {
                data
            }
        }
    }

    // var dataList = body .split("Ref")

    return refNumber

}

fun getFirstWord(text: String): String {
    val index = text.indexOf(' ')
    return if (index > -1) { // Check if there is more than one word.
        text.substring(0, index).trim { it <= ' ' } // Extract first word.
    } else {
        text// Text is the first word itself.
    }
}

fun checkSenderIsValid(sender: String): Boolean {
    return (sender.contains("ICICIB", true)
            || sender.contains("HDFCBK", true)
            || sender.contains("SBIINB", true)
            || sender.contains("SBMSMS", true)
            || sender.contains("SCISMS", true)
            || sender.contains("CBSSBI", true)
            || sender.contains("SBIPSG", true)
            || sender.contains("SBIUPI", true)
            || sender.contains("SBICRD", true)
            || sender.contains("ATMSBI", true)
            || sender.contains("QPMYAMEX", true)
            || sender.contains("IDFCFB", true)
            || sender.contains("UCOBNK", true)
            || sender.contains("CANBNK", true)
            || sender.contains("BOIIND", true)
            || sender.contains("AXISBK", true)
            || sender.contains("PAYTMB", true)
            || sender.contains("UnionB", true)
            || sender.contains("INDBNK", true)
            || sender.contains("KOTAKB", true)
            || sender.contains("CENTBK", true)
            || sender.contains("SCBANK", true)
            || sender.contains("PNBSMS", true)
            || sender.contains("DOPBNK", true)
            || sender.contains("YESBNK", true)
            || sender.contains("IDBIBK", true)
            || sender.contains("ALBANK", true)
            || sender.contains("CITIBK", true)
            || sender.contains("ANDBNK", true)
            || sender.contains("BOBTXN", true)
            || sender.contains("IOBCHN", true)
            || sender.contains("MAHABK", true)
            || sender.contains("OBCBNK", true)
            || sender.contains("RBLBNK", true)
            || sender.contains("RBLCRD", true)
            || sender.contains("SPRCRD", true)
            || sender.contains("HSBCBK", true)
            || sender.contains("HSBCIN", true)
            || sender.contains("DBSBNK", true)
            || sender.contains("SLCEIT", true)
            || sender.contains("UniCrd", true)
            || sender.contains("INDUSB", true))
}

fun checkSenderIsInValid(sender: String): Boolean {
    return !sender.contains("swiggy", true)
            && !sender.contains("zomato", true)
            && !sender.contains("ola", true)
            && !sender.contains("ubser", true)
            && !sender.contains("rapido", true)
}

fun getAccountNumber(body: String): String {
    when {
        body.contains("SBIDrCARD", true)
                && body.contains("tx#", true) -> {
            var dataList = body.toLowerCase().split("sbidrcard ")
            // val p1 =
            //    Pattern.compile("[0-9]*[Xx\\*]*[0-9]*[Xx\\*]+[0-9]{3,}")
            //val m1 = p1.matcher(dataList[1])
            var data = ""
            if (dataList.size > 1) {
                data = dataList[1].trim()
            } else {
                data = dataList[0]
            }
            return getFirstWord(data)
        }
        body.contains("Customer ID ", true) -> {
            var dataList = body.toLowerCase().split("customer id ")
            // val p1 =
            //    Pattern.compile("[0-9]*[Xx\\*]*[0-9]*[Xx\\*]+[0-9]{3,}")
            //val m1 = p1.matcher(dataList[1])
            var data = ""
            if (dataList.size > 1) {
                data = dataList[1]
            } else {
                data = dataList[0]
            }
            return getFirstWord(data)
        }
        body.contains("Deposit No ", true) -> {
            var dataList = body.toLowerCase().split("deposit no ")
            // val p1 =
            //    Pattern.compile("[0-9]*[Xx\\*]*[0-9]*[Xx\\*]+[0-9]{3,}")
            //val m1 = p1.matcher(dataList[1])
            var data = ""
            if (dataList.size > 1) {
                data = dataList[1]
            } else {
                data = dataList[0]
            }
            return getFirstWord(data)
        }
        body.contains("credit card ending", true) -> {
            var dataList = body.split("ending ")
            // val p1 =
            //    Pattern.compile("[0-9]*[Xx\\*]*[0-9]*[Xx\\*]+[0-9]{3,}")
            //val m1 = p1.matcher(dataList[1])
            var data = ""
            if (dataList.size > 1) {
                data = dataList[1]
            } else {
                data = dataList[0]
            }
            return getFirstWord(data)
        }
        body.contains("UPI", true) -> {
            var dataList = body.split("frm")
            val p1 =
                Pattern.compile("[0-9]*[Xx\\*]*[0-9]*[Xx\\*]+[0-9]{3,}")
            //val m1 = p1.matcher(dataList[1])
            var data = ""
            if (dataList.size == 2) {
                val m1 = p1.matcher(dataList[1])
                while (m1.find()) {
                    data = m1.group()
                    break
                }
            } else {
                val m1 = p1.matcher(dataList[0])
                while (m1.find()) {
                    data = m1.group()
                    break
                }
            }
            return data
        }
        body.contains("a/c", true) -> {
            if (body.contains("no.", true)) {
                var dataList = body.split("no.")
                val p1 =
                    Pattern.compile("[0-9]*[Xx\\*]*[0-9]*[Xx\\*]+[0-9]{3,}")
                var data = ""
                if (dataList.size == 2) {
                    val m1 = p1.matcher(dataList[1])
                    while (m1.find()) {
                        data = m1.group()
                        break
                    }
                } else {
                    val m1 = p1.matcher(dataList[0])
                    while (m1.find()) {
                        data = m1.group()
                        break
                    }
                }
                return data
            } else if (body.contains("A/c No", true)) {
                if (body.contains("XX", true)) {
                    var dataList = body.split("A/c No")
                    val p1 =
                        Pattern.compile("[0-9]*[Xx\\*]*[0-9]*[Xx\\*]+[0-9]{3,}")
                    var data = ""
                    if (dataList.size == 2) {
                        val m1 = p1.matcher(dataList[1])
                        while (m1.find()) {
                            data = m1.group()
                            break
                        }
                    } else {
                        val m1 = p1.matcher(dataList[0])
                        while (m1.find()) {
                            data = m1.group()
                            break
                        }
                    }
                    return data
                } else {
                    var dataList = body.split("a/c no ")
                    // val p1 =
                    //    Pattern.compile("[0-9]*[Xx\\*]*[0-9]*[Xx\\*]+[0-9]{3,}")
                    //val m1 = p1.matcher(dataList[1])
                    var data = ""
                    data = if (dataList.size > 1) {
                        dataList[1]
                    } else {
                        dataList[0]
                    }
                    return data.toLowerCase().split("as")[0]
                }

            } else if (body.contains("a/c no ", true)) {
                var dataList = body.split("a/c no ")
                // val p1 =
                //    Pattern.compile("[0-9]*[Xx\\*]*[0-9]*[Xx\\*]+[0-9]{3,}")
                //val m1 = p1.matcher(dataList[1])
                var data = ""
                data = if (dataList.size > 1) {
                    dataList[1]
                } else {
                    dataList[0]
                }
                return data.toLowerCase().split("as")[0]

            } else if (body.contains("a/c", true)) {
                if (!body.contains("xx", true)
                    && !body.contains("x", true)
                ) {

                    var dataList = body.toLowerCase().split("a/c ")
                    var data = ""
                    data = if (dataList.size > 1) {
                        dataList[1]
                    } else {
                        dataList[0]
                    }
                    return if (data.contains("as")) {
                        data.toLowerCase().split("as")[0]
                    } else {
                        getFirstWord(
                            data.toLowerCase().split("\\s")[0]
                        ).filter { it.isDigit() }
                    }
                    // return data
                } else {
                    var dataList = body.toLowerCase().split("a/c ")
                    val p1 =
                        Pattern.compile("[0-9]*[Xx\\*]*[0-9]*[Xx\\*]+[0-9]{3,}")
                    var data = ""
                    if (dataList.size > 1) {
                        val m1 = p1.matcher(dataList[1])
                        while (m1.find()) {
                            data = m1.group()
                            break
                        }
                    } else {
                        val m1 = p1.matcher(dataList[0])
                        while (m1.find()) {
                            data = m1.group()
                            break
                        }
                    }
                    return data
                }

            }
        }

        body.contains("Acct", true) -> {
            var dataList = body.split("Acct ")
            val p1 =
                Pattern.compile("[0-9]*[Xx\\*]*[0-9]*[Xx\\*]+[0-9]{3,}")
            var data = ""
            if (dataList.size > 1) {
                val m1 = p1.matcher(dataList[1])
                while (m1.find()) {
                    data = m1.group()
                    break
                }
            } else {
                val m1 = p1.matcher(dataList[0])
                while (m1.find()) {
                    data = m1.group()
                    break
                }
            }
            return data
        }
        body.contains("Card ending", true) -> {
            if (body.contains("ending", true)) {
                if (body.contains("XX", true)) {
                    var dataList =
                        body.toLowerCase().split("ending ")
                    val p1 =
                        Pattern.compile("[0-9]*[Xx\\*]*[0-9]*[Xx\\*]+[0-9]{3,}")
                    var data = ""
                    if (dataList.size > 1) {
                        val m1 = p1.matcher(dataList[1])
                        while (m1.find()) {
                            data = m1.group()
                            break
                        }
                    } else {
                        val m1 = p1.matcher(dataList[0])
                        while (m1.find()) {
                            data = m1.group()
                            break
                        }
                    }
                    return data
                } else {
                    var dataList =
                        body.toLowerCase().split("ending ")
                    var data = dataList[1].trim()
                    return getFirstWord(data)
                }

            } else if (body.contains("end", true)) {
                var dataList = body.toLowerCase().split("end")
                val p1 =
                    Pattern.compile("[0-9]*[Xx\\*]*[0-9]*[Xx\\*]+[0-9]{3,}")
                var data = ""
                if (dataList.size == 2) {
                    val m1 = p1.matcher(dataList[1])
                    while (m1.find()) {
                        data = m1.group()
                        break
                    }
                } else {
                    val m1 = p1.matcher(dataList[0])
                    while (m1.find()) {
                        data = m1.group()
                        break
                    }
                }
                return data
            }

        }
        body.contains("account", true) -> {
            var dataList = body.toLowerCase().split("account ")
            val p1 =
                Pattern.compile("[0-9]*[Xx\\*]*[0-9]*[Xx\\*]+[0-9]{3,}")
            var data = ""
            if (dataList.size > 1) {
                val m1 = p1.matcher(dataList[1])
                while (m1.find()) {
                    data = m1.group()
                    break
                }
            } else {
                val m1 = p1.matcher(dataList[0])
                while (m1.find()) {
                    data = m1.group()
                    break
                }
            }
            return data
        }

        body.contains("Card", true) -> {
            var dataList = body.toLowerCase().split("card")
            val p1 =
                Pattern.compile("[0-9]*[Xx\\*]*[0-9]*[Xx\\*]+[0-9]{3,}")
            var data = ""
            if (dataList.size == 2) {
                val m1 = p1.matcher(dataList[1])
                while (m1.find()) {
                    data = m1.group()
                    break
                }
            } else {
                val m1 = p1.matcher(dataList[0])
                while (m1.find()) {
                    data = m1.group()
                    break
                }
            }
            return data
        }

        body.contains("Ac", true) -> {
            var dataList = body.toLowerCase().split("ac ")
            val p1 =
                Pattern.compile("[0-9]*[Xx\\*]*[0-9]*[Xx\\*]+[0-9]{3,}")
            var data = ""
            if (dataList.size == 2) {
                val m1 = p1.matcher(dataList[1])
                while (m1.find()) {
                    data = m1.group()
                    break
                }
            } else {
                val m1 = p1.matcher(dataList[0])
                while (m1.find()) {
                    data = m1.group()
                    break
                }
            }
            return data
        }

    }
    return ""
}

fun checkIsTransactionSMS(smsBody: String): Boolean {
    return (smsBody.contains("withdrawn", true)
            || smsBody.contains("debited", true)
            || smsBody.contains("spent", true)
            || smsBody.contains("paying", true)
            || smsBody.contains("payment", true)
            || smsBody.contains("deducted", true)
            || smsBody.contains("debited", true)
            || smsBody.contains("purchase", true)
            || smsBody.contains("dr", true)
            || smsBody.contains("txn", true)
            || smsBody.contains("transfer", true)
            || smsBody.contains("credited", true)
            || smsBody.contains("cr", true)
            || smsBody.contains("deposited", true)
            || smsBody.contains("deposit", true)
            || smsBody.contains("received", true)
            && (
            !smsBody.contains("otp", true)
                    && !smsBody.contains("We are pleased to inform that", true)
                    && !smsBody.contains("has been opened", true)
                    && !smsBody.contains("emi", true)
            ))
}

fun isAppNameContains(sms: String): Boolean {
    return (sms.contains("dream11", ignoreCase = true)
            || sms.contains("My11Circle", ignoreCase = true)
            || sms.contains("RummyCircle", ignoreCase = true)
            || sms.contains("RummyCulture", ignoreCase = true)
            || sms.contains("A23", ignoreCase = true)
            || sms.contains("Ace2Three", true)
            )
}

fun extractMerchantNameFromSMS(mMessage: String) {
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

var dateFormat: DateFormat = SimpleDateFormat("yyyy-MM")

fun getCurrentMonth(): String {
    val date = Date()
    Log.d("Month", dateFormat.format(date))
    return dateFormat.format(date)
}

fun millisToDate(currentTime: Long): String {
    val finalDate: String
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = currentTime
    val date = calendar.time
    finalDate = dateFormat.format(date)
    return finalDate
}

fun getDateFromMilliseconds(timeInMillis: Long): Date {
    return Date(timeInMillis)
}

fun Double.decimalFormatter(): String {
    val formater = DecimalFormat("#.##")
    return formater.format(this)
}