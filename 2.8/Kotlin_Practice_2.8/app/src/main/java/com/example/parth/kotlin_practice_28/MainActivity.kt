package com.example.parth.kotlin_practice_28

import android.os.Bundle
import com.example.parth.kotlin_practice_28.databinding.ActivityMainBinding
import com.example.parth.kotlinpractice_2.support.CoreActivity
import com.support.kotlin.setUpBottomNavigation
import com.support.kotlin.string

class MainActivity : CoreActivity<MainActivity, ActivityMainBinding, MainViewModel>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setDefaults(this, R.layout.activity_main)
    }

    override fun setVM(binding: ActivityMainBinding) {
        binding.vm = viewModel
    }

    override fun createViewModel(activity: MainActivity): MainViewModel = MainViewModel(this@MainActivity)

    override fun getActionBarTitle(): String = R.string.preyansh_activity.string(this)

    override fun workArea(viewModel: MainViewModel) {

    }

    override fun bottomNavigation() {
        setUpBottomNavigation {
            setMenu(R.menu.bottom_navigation_menu)
            shiftModeEnabled(false)
            setBackgroundColor(R.drawable.selector_bottom_navigation)
            setItemColor(R.color.colorAccent, android.R.color.white)
            itemSelectionHandler {
                viewModel.onItemSelectedListener(it)
            }
            selectedItem(R.id.menu_btm_nav_recycler_view)
        }
    }
}
