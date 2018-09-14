package com.support.builders.SpinnerBuilder

import android.content.Context
import android.support.annotation.ArrayRes
import android.widget.ArrayAdapter
import android.widget.Spinner

fun Spinner.setUp(context: Context, builder: SpinnerBuilder.() -> Unit) = SpinnerBuilder(this, context).apply(builder)

class SpinnerBuilder(val spinner: Spinner, val context: Context) {

    val itemList: MutableList<String>? = null

    fun setItems(@ArrayRes itemArrayResId: Int) {
        val adapter: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource()
    }
}