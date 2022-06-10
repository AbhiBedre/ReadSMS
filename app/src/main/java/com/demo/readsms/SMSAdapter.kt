package com.demo.readsms

import android.annotation.SuppressLint
import android.content.Context
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.demo.readsms.extensions.decimalFormatter
import com.demo.readsms.extensions.millisToDate

class SMSAdapter(
    val context: Context,
    val isBankFilter: Boolean = false
) : RecyclerView.Adapter<SMSAdapter.ViewHolder>() {

    var list = arrayListOf<SMSModel>()
    var filterList = setOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.sms_items, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return if (isBankFilter) filterList.size else list.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (!isBankFilter) {
            val bal_text = if (list[position].transactionType == "debited") {
                "debited bal: ${list[position].balance}<br>"
            } else {
                "credited bal: ${list[position].balance}<br>"
            }
            holder.tvAddress.text = Html.fromHtml(
                "<h5>${list[position].address}</h5>" +
                        "msg body: ${list[position].body}<br><br>" +
                        "Avl Bal: ${list[position].avlBalance}<br>" +
                        bal_text +
                        "Card Type: ${list[position].cardType}<br>" +
//                        "${list[position].accNumber}<br>" +
                        "txn type: ${list[position].transactionType}<br>" +
                        "${list[position].refNumber}<br>" +
                        "${millisToDate(list[position].date)}"
            )
        } else {
            val bankList = list.filter { it.bankName.contains(filterList.elementAt(position)) }
            val avl_bal = if (bankList.none { it.avlBalance > 0.0 }) {
                0.0
            } else {
                bankList.first { it.avlBalance > 0.0 }.avlBalance ?: 0.0
            }
            val debitedAmt =
                bankList.filter {
                    it.transactionType == "debited"
                    /*&& millisToDate(it.date) == getCurrentMonth()*/
                }.sumOf { it.balance }
            val creditedAmt =
                bankList.filter { it.transactionType == "credited" /*&& millisToDate(it.date) == getCurrentMonth()*/ }
                    .sumOf { it.balance }
            holder.tvAddress.text =
                "${filterList.elementAt(position)}:\navl_bal: ${avl_bal.decimalFormatter()}\ndebitedAmt: ${debitedAmt.decimalFormatter()}\ncreditedAmt: ${creditedAmt.decimalFormatter()}"
        }
    }

    fun submitList(plans: List<SMSModel>) {
        this.list = plans as ArrayList<SMSModel>
        notifyDataSetChanged()
    }

    fun submitFilterList(filterList: Set<String>, list: ArrayList<SMSModel>) {
        this.list = list as ArrayList<SMSModel>
        this.filterList = filterList
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvAddress = itemView.findViewById<TextView>(R.id.tv_address)
    }
}