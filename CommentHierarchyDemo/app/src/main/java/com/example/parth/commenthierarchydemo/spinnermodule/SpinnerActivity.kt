package com.example.parth.commenthierarchydemo.spinnermodule

import android.os.Bundle
import com.example.parth.commenthierarchydemo.R
import com.example.parth.commenthierarchydemo.databinding.ActivitySpinnerBinding
import com.example.parth.kotlinpractice_2.support.CoreActivity

class SpinnerActivity : CoreActivity<SpinnerActivity, ActivitySpinnerBinding, SpinnerViewModel>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setDefaults(this@SpinnerActivity, R.layout.activity_spinner)
    }

    override fun setVM(binding: ActivitySpinnerBinding) {
        binding.vm = vm
    }

    override fun createViewModel(activity: SpinnerActivity) = SpinnerViewModel(this@SpinnerActivity)

    override fun getActionBarTitle() = "Spinner Activity"

    override fun workArea(vm: SpinnerViewModel) {

    }
}
