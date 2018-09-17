package com.example.parth.commenthierarchydemo.spinnermodule

import android.widget.Spinner
import com.example.parth.commenthierarchydemo.R
import com.example.parth.kotlinpractice_2.support.ActivityViewModel
import com.example.parth.kotlinpractice_2.support.CoreActivity
import com.support.builders.SpinnerBuilder.Builder

class SpinnerViewModel(val mActivity: CoreActivity<*, *, *>) : ActivityViewModel(mActivity) {
    fun setSpinner(spinner: Spinner) {
//        spinner.Builder {
//            setItems(R.array.planets_array, R.layout.spinner_selected_item_layout)
//            setDropDownViewLayout(R.layout.spinner_drop_down_item_layout)
//            populateSpinner()
//        }

        spinner.Builder(
                itemArrayResId = R.array.planets_array,
                itemLayout = R.layout.spinner_selected_item_layout,
                dropDownItemLayout = R.layout.spinner_drop_down_item_layout
        ).build()
    }
}