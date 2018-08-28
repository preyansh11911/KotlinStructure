package com.example.parth.commenthierarchydemo

import android.os.Bundle
import com.example.parth.commenthierarchydemo.MainViewModel.Type
import com.example.parth.commenthierarchydemo.MainViewModel.Type.COMMENT
import com.example.parth.commenthierarchydemo.databinding.ActivityMainBinding
import com.example.parth.kotlinpractice_2.support.CoreActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : CoreActivity<MainActivity, ActivityMainBinding, MainViewModel>() {

    var type: Type = COMMENT
    var itemPosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setDefaults(this, R.layout.activity_main)
    }

    override fun setVM(binding: ActivityMainBinding) {
        binding.vm = vm
    }

    override fun createViewModel(activity: MainActivity) = MainViewModel(this)

    override fun getActionBarTitle() = "MainActi"

    override fun workArea(vm: MainViewModel) {
        vm.createDRCList()
        img_btn_send.setOnClickListener {
            vm.addItemToList(ed_comment.text.toString(), type, itemPosition)
            ed_comment.text.clear()
            type = COMMENT
        }
    }
}