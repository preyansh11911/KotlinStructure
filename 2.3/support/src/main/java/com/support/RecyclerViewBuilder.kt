package com.support

import android.databinding.ViewDataBinding
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView


class RecyclerViewBuilder<T, U : ViewDataBinding>(recyclerView: RecyclerView, itemList: ArrayList<T>, onBound: (T, U: ViewDataBinding) -> Unit) {

    var itemView: Int = 0

    init {
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
        recyclerView.adapter = object : MyCustomRecyclerAdapter<T, U>(itemList) {
            override fun getVM(): Int = BR.vm

            override fun bindContent(binding: U, item: T) {
                onBound.invoke(item, binding)
            }

            override fun getItemView(): Int = itemView
        }
    }
}