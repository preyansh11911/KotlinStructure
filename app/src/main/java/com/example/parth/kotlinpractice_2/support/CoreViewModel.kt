package com.example.parth.kotlinpractice_2.support

import android.databinding.BaseObservable
import android.databinding.ObservableBoolean
import android.databinding.ObservableField

class CoreViewModel : BaseObservable(){
    var isCustomActionbar = ObservableBoolean(false)
    var actionBarTitle = ObservableField<String>("")
    var isBackEnabled = ObservableBoolean(false)
}