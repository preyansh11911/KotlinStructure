package com.example.parth.kotlinpractice_2.kotlin

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.Toast
import com.example.parth.kotlinpractice_2.R

fun Activity.launchActivity(intent: Intent) {
    this.startActivity(intent)
}

fun Context.showConfirmationDialog(icon: Int = R.drawable.ic_app_exit, title: String = "App Exit", message: String? = "Are you sure you want to exit from app?", posButtonText: String? = "YES", negButtonText: String = "", postiveClick: (() -> Unit)? = null, negativeClick: (() -> Unit)? = null
) {
    var alertDialog = AlertDialog.Builder(this)
    alertDialog.apply {
        icon.let { setIcon(it) }
        title.let { setTitle(it) }
        message?.let { setMessage(it) }
        posButtonText?.let { setPositiveButton(it) { dialog, which -> postiveClick?.invoke() } }
        if (negButtonText.isNotBlank())
            negButtonText.let { setNegativeButton(it) { dialog, which -> negativeClick?.invoke() } }
        create()
        show()
    }
}

fun Context.alert(alertDialog: AlertDialogBuilder.() -> Unit) = AlertDialogBuilder(this).apply(alertDialog)

class AlertDialogBuilder(context: Context) {

    lateinit var builder: AlertDialog.Builder
    lateinit var dialog: AlertDialog

    init {
        builder = AlertDialog.Builder(context)
        builder.setCancelable(false)
    }

    fun title(@StringRes title: Int) {
        builder.setTitle(title)
    }

    fun icon(iconRes: Int) {
        builder.setIcon(iconRes)
    }

    fun message(@StringRes msg: Int) {
        builder.setMessage(msg)
    }

    fun positiveButtonClick(@StringRes s: Int, listener: DialogInterface.() -> Unit) {
        builder.setPositiveButton(s) { dialog, which -> dialog.listener() }
    }

    fun negativeButtonClick(@StringRes s: Int, listener: DialogInterface.() -> Unit) {
        builder.setNegativeButton(s) { dialog, which -> dialog.listener() }
    }

    fun show() {
        dialog = builder.create()
        dialog.show()
        dialog.setCanceledOnTouchOutside(false)
    }

    fun makeCancelable() {
        builder.setCancelable(true)
    }

    fun makeCancelableOnTouchOutSide() {
        dialog.setCanceledOnTouchOutside(true)
    }
}

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