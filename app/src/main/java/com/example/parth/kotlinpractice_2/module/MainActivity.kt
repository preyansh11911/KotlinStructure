package com.example.parth.kotlinpractice_2.module

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.view.View
import com.example.parth.kotlinpractice_2.R
import com.example.parth.kotlinpractice_2.databinding.ActivityMainBinding
import com.example.parth.kotlinpractice_2.databinding.NavHeaderDrawerBinding
import com.example.parth.kotlinpractice_2.kotlin.setBottomNavigation
import com.example.parth.kotlinpractice_2.support.CoreActivity

class MainActivity : CoreActivity<MainActivity,ActivityMainBinding, MainViewModel>() {

    companion object {
        fun getIntent(context: Context): Intent {
            var intent = Intent(context, MainActivity::class.java)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setDefaults(this,R.layout.activity_main)
    }

    override fun setVM(binding: ActivityMainBinding) {
        binding.vm = viewModel
    }

    override fun createViewModel(activity: MainActivity): MainViewModel = MainViewModel(activity)

    override fun getActionBarTitle(): String = getString(R.string.main_activity_title)

    override fun hasNavigationDrawer(): Boolean = true

    override fun setNavigationDrawerMenu(navigationView: NavigationView) {
        navigationView.inflateMenu(R.menu.activity_drawer_drawer)
    }

    override fun setNavigationDrawerHeader(navigationView: NavigationView) {
        val headerView: View = navigationView.inflateHeaderView(R.layout.nav_header_drawer)
        val navHeaderBinding = DataBindingUtil.bind<NavHeaderDrawerBinding>(headerView)
        navHeaderBinding?.vm = viewModel
    }

    override fun bottomNavigation() {
        this.setBottomNavigation {
            setMenu(R.menu.bottom_navigation)
            setBackgroundColor(R.drawable.bottom_navigation_background_color)
            setItemColor(R.color.colorAccent, android.R.color.white)
            shiftModeEnabled(false)
        }
    }
}
