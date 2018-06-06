package com.example.parth.kotlinpractice_2.module

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.NavigationView
import com.example.parth.kotlinpractice_2.R
import com.example.parth.kotlinpractice_2.databinding.ActivityMainBinding
import com.example.parth.kotlinpractice_2.databinding.NavHeaderDrawerBinding
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

    override fun createViewModel(activity: MainActivity): MainViewModel {
        return MainViewModel(activity)
    }

    override fun getActionBarTitle(): String {
        return "Preyansh Activity"
    }

    override fun hasNavigationDrawer(): Boolean {
        return true
    }

    override fun setNavigationDrawerMenu(navigationView: NavigationView) {
        navigationView.inflateMenu(R.menu.activity_drawer_drawer)
    }

    override fun setNavigationDrawerHeader(navigationView: NavigationView) {
        val navHeaderBinding = DataBindingUtil.inflate<NavHeaderDrawerBinding>(
                activity.layoutInflater, R.layout.nav_header_drawer, null, false)
        navigationView.addHeaderView(navHeaderBinding.root)
        navHeaderBinding.vm = viewModel
    }

    override fun setBottomNavDrawerMenu(bottomNavigation: BottomNavigationView) {
        bottomNavigation.inflateMenu(R.menu.bottom_navigation_menu)
    }

}
