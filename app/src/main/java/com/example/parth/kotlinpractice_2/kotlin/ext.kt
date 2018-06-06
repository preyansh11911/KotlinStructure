package com.example.parth.kotlinpractice_2.kotlin

import android.app.Activity
import android.content.Intent

fun Activity.launchActivity(intent: Intent) {
    this.startActivity(intent)
}