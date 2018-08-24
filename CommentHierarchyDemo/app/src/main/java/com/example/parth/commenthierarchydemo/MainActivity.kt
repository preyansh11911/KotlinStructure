package com.example.parth.commenthierarchydemo

import android.os.Bundle
import com.example.parth.commenthierarchydemo.databinding.ActivityMainBinding
import com.example.parth.kotlinpractice_2.support.CoreActivity

class MainActivity : CoreActivity<MainActivity,ActivityMainBinding,MainViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setDefaults(this,R.layout.activity_main)
    }

    override fun setVM(binding: ActivityMainBinding) {
        binding.vm = vm
    }

    override fun createViewModel(activity: MainActivity) = MainViewModel(this)

    override fun getActionBarTitle() = "MainActi"

    override fun workArea(vm: MainViewModel) {
        vm.createDRCList()
    }
}
