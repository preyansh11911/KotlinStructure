package com.example.parth.kotlinpractice_2.module

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.parth.kotlinpractice_2.R
import com.example.parth.kotlinpractice_2.databinding.ActivitySecondBinding
import com.example.parth.kotlinpractice_2.support.Constants
import com.example.parth.kotlinpractice_2.support.CoreActivity

class SecondActivity : CoreActivity<SecondActivity, ActivitySecondBinding, SecondViewModel>() {

    companion object {
        fun getIntent(context: Context): Intent {
            var intent = Intent(context, SecondActivity::class.java)
            return intent
        }

        fun getIntent(context: Context, isBackEnabled: Boolean): Intent {
            var intent = Intent(context, SecondActivity::class.java)
            intent.putExtra(Constants.BACK_KEY, isBackEnabled)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setDefaults(this, R.layout.activity_second)
    }

    override fun setVM(binding: ActivitySecondBinding) {
        binding.vm = viewModel
    }

    override fun createViewModel(activity: SecondActivity): SecondViewModel {
        return SecondViewModel(activity)
    }

    override fun getActionBarTitle(): String {
        return "Second Activity"
    }

    override fun isBackEnabled(): Boolean {
        return intent.getBooleanExtra(Constants.BACK_KEY, false)
    }
}
