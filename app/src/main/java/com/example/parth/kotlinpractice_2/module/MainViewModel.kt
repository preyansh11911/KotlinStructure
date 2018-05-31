package com.example.parth.kotlinpractice_2.module

import android.app.Activity
import android.databinding.ObservableField
import com.example.parth.kotlinpractice_2.support.ActivityViewModel

class MainViewModel(activity: Activity) : ActivityViewModel(){
    var text = ObservableField<String>("Preyansh")
}