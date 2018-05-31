package com.example.parth.kotlinpractice_2.support

import android.app.Activity
import android.databinding.BaseObservable
import android.databinding.Observable
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import com.example.parth.kotlinpractice_2.R

open class ActivityViewModel : BaseObservable() {
    var isCustomActionbar = ObservableBoolean(true)
    var actionBarTitle = ObservableField<String>("This is Preyansh ToolBar")
    var isBackEnabled = ObservableBoolean(true)
}