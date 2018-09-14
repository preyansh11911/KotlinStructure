package com.example.parth.commenthierarchydemo.spinnermodule

import android.widget.Spinner
import com.example.parth.kotlinpractice_2.support.ActivityViewModel
import com.example.parth.kotlinpractice_2.support.CoreActivity
import com.support.builders.SpinnerBuilder.setUp

class SpinnerViewModel(val mActivity: CoreActivity<*, *, *>) : ActivityViewModel(mActivity) {
    fun setSpinner(spinner: Spinner) {
        spinner.setUp {

        }
    }
}