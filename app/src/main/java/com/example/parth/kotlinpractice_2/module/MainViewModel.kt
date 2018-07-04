package com.example.parth.kotlinpractice_2.module

import android.databinding.ObservableField
import android.view.MenuItem
import android.view.View
import com.example.parth.kotlinpractice_2.R
import com.example.parth.kotlinpractice_2.camera.CameraFragment
import com.example.parth.kotlinpractice_2.gallery.GalleryFragment
import com.example.parth.kotlinpractice_2.kotlin.startFragment
import com.example.parth.kotlinpractice_2.slideshow.SlideshowFragment
import com.example.parth.kotlinpractice_2.support.ActivityViewModel
import com.example.parth.kotlinpractice_2.tools.ToolsFragment

class MainViewModel(val coreActivity: MainActivity) : ActivityViewModel(coreActivity) {
    var text = ObservableField<String>("Preyansh from MainViewModel")

    fun onSecondClick(view: View) {
        startActivity(SecondActivity.getIntent(coreActivity, isBackEnabled = true))
    }

    fun launchCameraFrag() {
        coreActivity.startFragment(CameraFragment(), title = "Camera Fragment")
    }

    fun bottomNavigationClickHandler(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.bottom_nav_camera -> {
                coreActivity.startFragment(CameraFragment(), title = "Camera Fragment")
                return true
            }
            R.id.bottom_nav_gallery -> {
                coreActivity.startFragment(GalleryFragment(), title = "Gallery Fragment")
                return true
            }
            R.id.bottom_nav_manage -> {
                coreActivity.startFragment(ToolsFragment(), title = "Tools Fragment")
                return true
            }
            R.id.bottom_nav_slideshow -> {
                coreActivity.startFragment(SlideshowFragment(), title = "Slideshow Fragment")
                return true
            }
        }
        return true
    }

    fun navigationDrawerClickHandler(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.nav_camera -> {
                coreActivity.startFragment(CameraFragment(), title = "Camera Fragment")
                return true
            }
            R.id.nav_gallery -> {
                coreActivity.startFragment(GalleryFragment(), title = "Gallery Fragment")
                return true
            }
            R.id.nav_manage -> {
                coreActivity.startFragment(ToolsFragment(), title = "Tools Fragment")
                return true
            }
            R.id.nav_slideshow -> {
                coreActivity.startFragment(SlideshowFragment(), title = "Slideshow Fragment")
                return true
            }
        }

        coreActivity.onBackPressed()
        return true
    }
}