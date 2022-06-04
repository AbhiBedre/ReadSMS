package com.demo.readsms

import android.annotation.SuppressLint
import android.content.Context
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

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
            holder.tvAddress.text = Html.fromHtml(
                "<h5>${list[position].address}</h5>" +
                        "msg body: ${list[position].body}<br><br>" +
                        "Avl Bal: ${list[position].avlBalance}<br>" +
                        "bal: ${list[position].balance}<br>" +
                        "${list[position].cardType}<br>" +
                        "${list[position].accNumber}<br>" +
                        "${list[position].transactionType}<br>" +
                        "${list[position].refNumber}<br>" +
                        "${list[position].date}"
            )
        } else {
            val bankList = list.filter { it.address.contains(filterList.elementAt(position)) }
            val avl_bal = if (bankList.none { it.avlBalance > 0.0 }) {
                0.0
            } else {
                bankList.first { it.avlBalance > 0.0 }.avlBalance ?: 0.0
            }
            val debitedAmt = bankList.sumOf { it.balance }
            holder.tvAddress.text =
                "${filterList.elementAt(position)}:\navl_bal: ${avl_bal}\ndebitedAmt: ${debitedAmt}"
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