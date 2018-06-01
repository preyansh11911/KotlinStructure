package com.example.parth.kotlinpractice_2.support

import android.app.Activity
import android.databinding.BaseObservable
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.support.design.widget.NavigationView
import android.support.v4.content.ContextCompat.startActivity
import android.support.v4.view.GravityCompat
import android.view.View
import android.widget.Toast
import com.example.parth.kotlinpractice_2.R.id.drawer_layout
import com.example.parth.kotlinpractice_2.module.MainActivity
import kotlinx.android.synthetic.main.activity_drawer.*

open class CoreViewModel(_activity: Activity) : BaseObservable() {
    var activity = _activity
    var isCustomActionbar = ObservableBoolean(false)
    var actionBarTitle = ObservableField<String>("zxcvb")
    var isBackEnabled = ObservableBoolean(false)
    var hasNavigationDrawer = ObservableBoolean(false)
    var hasBottom_DrawerNav = ObservableBoolean(false)
    var hasBottomNavigation = ObservableBoolean(false)

    fun setNavigationDrawerMenu(navigationView: NavigationView, menu: Int) {
        navigationView.inflateMenu(menu)
    }

    fun setNavigationDrawerHeader(navigationView: NavigationView, headerView: Int?) {
        headerView?.let { navigationView.inflateHeaderView(it) }
    }

    fun onBackPressed(view: View) {
        onBackPressed()
    }

    fun onBackPressed() {
        if (activity.drawer_layout.isDrawerOpen(GravityCompat.START)) {
            activity.drawer_layout.closeDrawer(GravityCompat.START)
        } else if (activity is MainActivity) {
            Toast.makeText(activity,"On Back Pressed",Toast.LENGTH_SHORT).show()
        } else {
            activity.onBackPressed()
        }
    }
}