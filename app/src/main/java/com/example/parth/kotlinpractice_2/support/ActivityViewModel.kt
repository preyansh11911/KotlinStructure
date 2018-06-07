package com.example.parth.kotlinpractice_2.support

import android.app.Activity
import android.content.Intent
import android.databinding.BaseObservable
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.view.View

open class ActivityViewModel(_activity: Activity) : BaseObservable() {
    var activity = _activity
    var isCustomActionbar = ObservableBoolean(false)
    var actionBarTitle = ObservableField<String>("zxcvb")
    var isBackEnabled = ObservableBoolean(false)
    var hasNavigationDrawer = ObservableBoolean(false)
    var hasBottom_DrawerNav = ObservableBoolean(false)
    var hasBottomNavigation = ObservableBoolean(false)

    fun onBackPressed(view: View) {
        activity.onBackPressed()
    }

//    fun onBackPressed() {
//
//        activity.drawer_layout.let { if (it.isDrawerOpen(GravityCompat.START)) it.closeDrawer(GravityCompat.START) }
//
//        if (!isBackEnabled.get()) {
//            Toast.makeText(activity, "On Back Pressed", Toast.LENGTH_SHORT).show()
//        } else {
//            activity.onBackPressed()
//        }
//    }

    fun startActivity(intent: Intent) {
        activity.startActivity(intent)
        if (!intent.getBooleanExtra(Constants.BACK_KEY, true))
            activity.finish()
    }
}
