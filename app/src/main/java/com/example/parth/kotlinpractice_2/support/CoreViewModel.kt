package com.example.parth.kotlinpractice_2.support

import android.databinding.BaseObservable
import android.databinding.ObservableBoolean
import android.databinding.ObservableField

open class CoreViewModel : BaseObservable() {
    var isCustomActionbar = ObservableBoolean(false)
    var actionBarTitle = ObservableField<String>("zxcvb")
    var isBackEnabled = ObservableBoolean(false)
    var hasNavigationDrawer = ObservableBoolean(false)
    var navigationDrawerMenu = ObservableField<Int>()

}