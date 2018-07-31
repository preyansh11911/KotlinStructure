package com.example.parth.kotlin_practice_28

import android.support.design.widget.BottomNavigationView
import android.view.MenuItem
import com.example.parth.kotlinpractice_2.support.ActivityViewModel
import com.support.kotlin.startFragment
import com.support.setUp
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_row_main.view.*

class MainViewModel(val mActivity: MainActivity) : ActivityViewModel(mActivity) {

    fun onItemSelectedListener(menuItem: MenuItem) {
        when (menuItem.itemId) {
            R.id.menu_btm_nav_recycler_view -> {
                mActivity.startFragment(RecyclerListFragment(),"RecyclerList", false, R.id.main_activity_box)
            }
        }
    }
}