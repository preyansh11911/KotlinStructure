package com.example.parth.kotlinpractice_2.module

import android.databinding.ObservableField
import android.os.Handler
import android.view.MenuItem
import android.view.View
import com.example.parth.kotlinpractice_2.R
import com.example.parth.kotlinpractice_2.kotlin.Duration
import com.example.parth.kotlinpractice_2.kotlin.toast
import com.example.parth.kotlinpractice_2.support.ActivityViewModel

class MainViewModel(_activity: MainActivity) : ActivityViewModel(_activity) {
    var coreActivity = _activity
    var text = ObservableField<String>("Preyansh from MainViewModel")

    var navHeaderTitle = ObservableField<String>("")
    var navHeaderContent = ObservableField<String>("")

    init {
        Handler().postDelayed(Runnable {
            coreActivity.setActionBarTitle("New Title")

            navHeaderTitle.set("Navigation Title")
            navHeaderContent.set("This is Navigation header content")
        }, 3000)

    }

    fun onSecondClick(view: View) {
        startActivity(SecondActivity.getIntent(coreActivity, isBackEnabled = true))
    }

    fun bottomNavigationClickHandler(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.bottom_nav_camera -> {
                coreActivity.toast("Tab CAMERA clicked", Duration.SHORT)
                return true
            }
            R.id.bottom_nav_gallery -> {
                coreActivity.toast("Tab GALLERY clicked", Duration.SHORT)
                return true
            }
            R.id.bottom_nav_manage -> {
                coreActivity.toast("Tab MANAGE clicked", Duration.SHORT)
                return true
            }
            R.id.bottom_nav_slideshow -> {
                coreActivity.toast("Tab SLIDESHOW clicked", Duration.SHORT)
                return true
            }
        }
        return false
    }
}