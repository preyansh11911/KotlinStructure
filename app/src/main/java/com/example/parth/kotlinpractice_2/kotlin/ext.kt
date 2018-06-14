package com.example.parth.kotlinpractice_2.kotlin

import android.content.Context
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.View
import android.widget.Toast
import com.example.parth.kotlinpractice_2.support.AlertDialogBuilder
import com.example.parth.kotlinpractice_2.support.BottomNavigationBuilder
import com.example.parth.kotlinpractice_2.support.CoreActivity

fun Context.alert(alertDialog: AlertDialogBuilder.() -> Unit) = AlertDialogBuilder(this).apply(alertDialog)

fun Fragment.toast(msg: String, duration: Duration) {
    activity?.toast(msg, duration)
}

fun Context.toast(msg: String, duration: Duration) {
    val dur: Int
    when (duration) {
        Duration.LONG -> dur = Toast.LENGTH_LONG
        Duration.SHORT -> dur = Toast.LENGTH_SHORT
        else -> {
            dur = Toast.LENGTH_SHORT
        }
    }
    Toast.makeText(this, msg, dur).show()
}

fun snack(v: View, msg: String) {
    Snackbar.make(v, msg, Snackbar.LENGTH_SHORT)
}

enum class Duration {
    SHORT, LONG, INDEFINITE
}

fun CoreActivity<*, *, *>.setBottomNavigation(builder: BottomNavigationBuilder.() -> Unit) = BottomNavigationBuilder(this).apply(builder)
