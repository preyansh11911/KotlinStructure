package com.example.parth.kotlinpractice_2.kotlin

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AlertDialog
import com.example.parth.kotlinpractice_2.R

fun Activity.launchActivity(intent: Intent) {
    this.startActivity(intent)
}

interface ConfirmationDialogClickListener {
    fun onNegativeButtonClick(dialog: DialogInterface)
    fun onPositiveButtonClick(dialog: DialogInterface)
}

interface ConfirmationDialogListener {
    fun onNegativeButtonClick()
}

/** The identifier for the positive button.  */
val BUTTON_POSITIVE = -1

/** The identifier for the negative button.  */
val BUTTON_NEGATIVE = -2

fun Context.showConfirmationDialog(icon: Int = R.drawable.ic_app_exit, title: String = "", message: String? = "Are you sure you want to exit from app?", posButtonText: String? = "YES", negButtonText: String = "NO", listner: (Int) -> Unit) {
//    listner.invoke()
    var alertDialog = AlertDialog.Builder(this)
    alertDialog.apply {
        icon.let { setIcon(it) }

        if (title.isNotBlank()) {
            setTitle(title)
        }

        message?.let { setMessage(it) }
        posButtonText?.let { setPositiveButton(it) { dialog, which -> listner.invoke(BUTTON_POSITIVE) } }
        negButtonText.let { setNegativeButton(it) { dialog, which -> listner.invoke(BUTTON_NEGATIVE) } }
        create()
        show()
    }
}

fun Context.showConfirmationDialog(icon: Int = R.drawable.ic_app_exit, title: String = "App Exit", message: String? = "Are you sure you want to exit from app?", posButtonText: String? = "YES", negButtonText: String = "", postiveClick: ((s: String) -> Unit)? = null, negativeClick: ((String) -> Unit)? = null) {
    var alertDialog = AlertDialog.Builder(this)
    alertDialog.apply {
        icon.let { setIcon(it) }
        title.let { setTitle(it) }
        message?.let { setMessage(it) }
        posButtonText?.let { setPositiveButton(it) { dialog, which -> postiveClick?.invoke("sjk") } }
        if (negButtonText.isNotBlank())
            negButtonText.let { setNegativeButton(it) { dialog, which -> negativeClick?.invoke("text") } }
        create()
        show()
    }
}

//fun Context.showConfirmationDialog(listener: (DialogInterface)->Unit) {
//
//    var alertDialog = AlertDialog.Builder(this)
////    alertDialog.apply {
////        icon?.let { setIcon(it) }
////        title?.let { setTitle(it) }
////        message?.let { setMessage(it) }
//////        posButtonText?.let { setPositiveButton(it) {dialog, which -> listener.run { listener.onPo} }}
////        negButtonText?.let { setNegativeButton(it) {dialog, which -> listener.invoke(dialog) }}
////        create()
////        show()
////    }
//}

//fun Context.showConfirmationDialog(listener: ()->Unit) {
//
//    listener.invoke(d)
//
//    var alertDialog = AlertDialog.Builder(this)
///*    alertDialog.apply {
//        icon?.let { setIcon(it) }
//        title?.let { setTitle(it) }
//        message?.let { setMessage(it) }
////        posButtonText?.let { setPositiveButton(it) {dialog, which -> listener.run { listener.onPo} }}
//        negButtonText?.let { setNegativeButton(it) {dialog, which -> listener.onNegativeButtonClick() }}
//        create()
//        show()*/
//    }
//}