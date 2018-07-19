package com.support

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

abstract class MyCustomRecyclerAdapter<T, U : ViewDataBinding>(
        val mItems: ArrayList<T>) : RecyclerView.Adapter<MyCustomRecyclerAdapter<T, U>.MyCustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCustomViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<U>(inflater, viewType, parent, false)
        return MyCustomViewHolder(binding)
    }

    override fun getItemCount(): Int = mItems.size

    override fun onBindViewHolder(holder: MyCustomViewHolder, position: Int) {
        //holder.bind(mItems[position])
        holder.binding.setVariable(getVM(), mItems[position])
        holder.binding.executePendingBindings()
        bindContent(holder.binding, mItems[position])
    }

    override fun getItemViewType(position: Int): Int {
        return getItemView()
    }

    abstract fun bindContent(binding: U, item: T)

    @LayoutRes
    abstract fun getItemView(): Int

    abstract fun getVM(): Int

    inner class MyCustomViewHolder(val binding: U) : RecyclerView.ViewHolder(binding.root)
}