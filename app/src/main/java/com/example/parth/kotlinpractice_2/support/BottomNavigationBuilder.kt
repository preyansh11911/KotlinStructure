package com.example.parth.kotlinpractice_2.support

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.support.design.internal.BottomNavigationItemView
import android.support.design.internal.BottomNavigationMenuView
import android.support.design.widget.BottomNavigationView
import android.util.Log
import kotlinx.android.synthetic.main.content_drawer.*


class BottomNavigationBuilder(val coreActivity: CoreActivity<*, *, *>) {

    var view: BottomNavigationView

    init {

        if (coreActivity.hasNavigationDrawer()) {
            coreActivity.coreViewModel.hasBottom_DrawerNav.set(true)
            view = coreActivity.bottom_navigation_nav_drawer
        } else {
            coreActivity.coreViewModel.hasBottomNavigation.set(true)
            view = coreActivity.bottom_navigation_nav_drawer
        }
    }

    fun setMenu(menuResID: Int) {
        view.inflateMenu(menuResID)
    }

    fun setBackgroundColor(resID: Int) {
        view.itemBackgroundResource = resID
    }

    fun setItemColor(colorStateList: () -> ColorStateList) {
        view.itemIconTintList = colorStateList()
        view.itemTextColor = colorStateList()
    }


    @SuppressLint("RestrictedApi")
    fun shiftModeEnabled(isShifting: Boolean) {
        if (!isShifting) {
            val menuView = view.getChildAt(0) as BottomNavigationMenuView
            try {
                val shiftingMode = menuView.javaClass.getDeclaredField("mShiftingMode")
                shiftingMode.isAccessible = true
                shiftingMode.setBoolean(menuView, false)
                shiftingMode.isAccessible = false
                for (i in 0 until menuView.childCount) {
                    val item = menuView.getChildAt(i) as BottomNavigationItemView

                    item.setShiftingMode(false)
                    // set once again checked value, so view will be updated

                    item.setChecked(item.itemData.isChecked)
                }
            } catch (e: NoSuchFieldException) {
                Log.e("BNVHelper", "Unable to get shift mode field", e)
            } catch (e: IllegalAccessException) {
                Log.e("BNVHelper", "Unable to change value of shift mode", e)
            }
        }
    }
}

