package com.example.parth.kotlinpractice_2.module

import android.app.Activity
import android.databinding.ObservableField
import com.example.parth.kotlinpractice_2.support.CoreViewModel

class MainViewModel(_activity: Activity) : CoreViewModel(_activity) {
    var text = ObservableField<String>("Preyansh from MainViewModel")
}