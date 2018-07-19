package com.example.parth.kotlin_practice_27

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.row_item.view.*

class SampleAdapter(
        val mContext: Context,
        val items: ArrayList<SampleModel>,
        val layoutResID : Int) : RecyclerView.Adapter<SampleAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(mContext).inflate(layoutResID,parent,false))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(items[position])
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(sModel: SampleModel) {
            itemView.txt_item.text = sModel.name
        }
    }
}