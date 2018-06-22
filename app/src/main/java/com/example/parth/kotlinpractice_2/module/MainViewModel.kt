package com.example.parth.kotlinpractice_2.module

import android.databinding.ObservableField
import android.view.MenuItem
import android.view.View
import com.example.parth.kotlinpractice_2.R
import com.example.parth.kotlinpractice_2.camera.CameraFragment
import com.example.parth.kotlinpractice_2.kotlin.Duration
import com.example.parth.kotlinpractice_2.kotlin.launchFragment
import com.example.parth.kotlinpractice_2.kotlin.toast
import com.example.parth.kotlinpractice_2.support.ActivityViewModel

class MainViewModel(val coreActivity: MainActivity) : ActivityViewModel(coreActivity) {
    var text = ObservableField<String>("Preyansh from MainViewModel")

    fun onSecondClick(view: View) {
        startActivity(SecondActivity.getIntent(coreActivity, isBackEnabled = true))
    }

    fun launchCameraFrag() {
        CameraFragment.newInstance().let {
            coreActivity.launchFragment(title = "Camera Fragment", addToBackStack = false, fragment = it, containerId = R.id.box_home_layout)
        }
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