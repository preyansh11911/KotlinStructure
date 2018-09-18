package com.example.parth.commenthierarchydemo.spinnermodule

import android.widget.Spinner
import com.example.parth.commenthierarchydemo.R
import com.example.parth.kotlinpractice_2.support.ActivityViewModel
import com.example.parth.kotlinpractice_2.support.CoreActivity
import com.support.builders.SpinnerBuilder.Builder
import kotlinx.android.synthetic.main.spinner_selected_item_layout.view.*
import java.util.*

class SpinnerViewModel(val mActivity: CoreActivity<*, *, *>) : ActivityViewModel(mActivity) {
    fun setSpinner(spinner: Spinner) {
        spinner.Builder(
                itemArrayResId = R.array.planets_array,
                itemLayout = R.layout.spinner_selected_item_layout,
                dropDownItemLayout = R.layout.spinner_drop_down_item_layout
        ) {
            contentBinder { s, view -> view.txt_item_1.text = s.toString() }
            setBackground(R.drawable.rounded_edit_text)
        }
    }

    fun makeList(): MutableList<String> {
        return Arrays.asList("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday")
    }

}
