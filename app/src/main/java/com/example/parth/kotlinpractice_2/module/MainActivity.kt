package com.example.parth.kotlinpractice_2.module

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.parth.kotlinpractice_2.R
import com.example.parth.kotlinpractice_2.databinding.ActivityMainBinding
import com.example.parth.kotlinpractice_2.support.CoreActivity
import com.example.parth.kotlinpractice_2.support.CoreViewModel

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

//        startActivity(DrawerActivity.getIntent(this))
    }

    override fun setVM(binding: ActivityMainBinding) {
        binding.vm = viewModel
    }

    override fun createViewModel(activity: MainActivity): MainViewModel {
        return MainViewModel(activity)
    }

    override fun hasActionbar(): Boolean {
        return true
    }

    override fun isCustomActionbar(): Boolean {
        return true
    }

    override fun isBackEnabled(): Boolean {
        return true
    }

    override fun getActionBarTitle(): String {
        return "Presh Activity"
    }

    override fun hasNavigationDrawer(): Boolean {
        return true
    }

    override fun setNavigationDrawerMenu(coreViewModel: CoreViewModel) {
        coreViewModel.navigationDrawerMenu.set(R.menu.bottom_navigation_menu)
    }
}
