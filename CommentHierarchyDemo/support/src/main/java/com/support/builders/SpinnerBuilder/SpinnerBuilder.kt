package com.support.builders.SpinnerBuilder

import android.support.annotation.ArrayRes
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.Spinner
import com.example.parth.kotlinpractice_2.support.CoreActivity

fun Spinner.Builder(
        @ArrayRes itemArrayResId: Int? = null,
        itemList: MutableList<*>? = null,
        @LayoutRes itemLayout: Int? = null,
        @LayoutRes dropDownItemLayout: Int? = null,
        builder: (SpinnerBuilder.() -> Unit)? = null
) = SpinnerBuilder(this,
        itemArrayResId,
        itemList,
        itemLayout,
        dropDownItemLayout).apply { builder }

class SpinnerBuilder(
        val spinner: Spinner,
        @ArrayRes val itemArrayResId: Int? = null,
        val itemList: MutableList<*>? = null,
        @LayoutRes var itemLayout: Int? = null,
        @LayoutRes var dropDownItemLayout: Int? = null
) : BaseAdapter() {

    var contentBindingListener: ((Any, View) -> Unit)? = null

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = LayoutInflater.from(CoreActivity.instances).inflate(itemLayout!!, parent, false)
        contentBindingListener?.invoke(itemList?.get(position)!!, view)
        return view
    }

    override fun getItem(position: Int) = itemList?.get(position)

    override fun getItemId(position: Int) = 0.toLong()

    override fun getCount() = itemList!!.size

    lateinit var adapter: ArrayAdapter<*>
    var customAdapter: BaseAdapter = this

    init {
        if (itemArrayResId != null) {
            if (itemLayout != null)
                adapter = ArrayAdapter.createFromResource(CoreActivity.instances, itemArrayResId, itemLayout!!)
            else
                adapter = ArrayAdapter.createFromResource(CoreActivity.instances, itemArrayResId, android.R.layout.simple_spinner_dropdown_item)

            if (dropDownItemLayout != null)
                adapter.setDropDownViewResource(dropDownItemLayout!!)
            else
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        } else if (itemList != null) {
            if (itemLayout == null)
                itemLayout = android.R.layout.simple_spinner_dropdown_item
            if (dropDownItemLayout == null)
                dropDownItemLayout = android.R.layout.simple_spinner_dropdown_item
        }

    }

    fun contentBinder(l: (Any, View) -> Unit) {
        contentBindingListener = l
    }

    fun setItems(@ArrayRes itemArrayResId: Int, @LayoutRes itemLayout: Int) {
        val adapter: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(CoreActivity.instances, itemArrayResId, itemLayout)
        this.adapter = adapter
    }

    fun setDropDownViewLayout(@LayoutRes itemLayout: Int) {
        adapter.setDropDownViewResource(itemLayout)
    }

    fun build() {
        if (itemArrayResId != null)
            spinner.adapter = adapter
        else if (itemList != null)
            spinner.adapter = customAdapter
    }
}