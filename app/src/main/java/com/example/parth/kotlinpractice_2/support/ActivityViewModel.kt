package com.example.parth.kotlinpractice_2.support

import android.app.Activity
import android.content.Intent
import android.databinding.BaseObservable
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.support.v4.view.GravityCompat
import android.view.View
import android.widget.Toast

open class ActivityViewModel(_activity: Activity) : BaseObservable() {
    var activity = _activity
    var isCustomActionbar = ObservableBoolean(false)
    var actionBarTitle = ObservableField<String>("zxcvb")
    var isBackEnabled = ObservableBoolean(false)
    var hasNavigationDrawer = ObservableBoolean(false)
    var hasBottom_DrawerNav = ObservableBoolean(false)
    var hasBottomNavigation = ObservableBoolean(false)

    fun onBackPressed(view: View) {
        onBackPressed()
    }

    fun onBackPressed() {

        activity.drawer_layout.let { if (it.isDrawerOpen(GravityCompat.START)) it.closeDrawer(GravityCompat.START) }

        if (!isBackEnabled.get()) {
            Toast.makeText(activity, "On Back Pressed", Toast.LENGTH_SHORT).show()
        } else {
            activity.onBackPressed()
        }
    }

    fun startActivity(intent: Intent) {
        activity.startActivity(intent)
    }
}
