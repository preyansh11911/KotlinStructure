package com.example.parth.kotlinpractice_2.support.binding

import android.databinding.BindingAdapter
import android.support.design.widget.BottomNavigationView

class BindingHelper {

    /**
     * @param bottomNavigationView
     * @param listener
     */
    @BindingAdapter("bottomNavigationListener")
    fun setBottomNavigationListener(bottomNavigationView: BottomNavigationView,
                                    listener: BottomNavigationView.OnNavigationItemSelectedListener?) {
        if (listener != null)
            bottomNavigationView.setOnNavigationItemSelectedListener(listener)
    }

}