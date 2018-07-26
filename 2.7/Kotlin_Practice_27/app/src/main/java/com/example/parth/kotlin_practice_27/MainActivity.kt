package com.example.parth.kotlin_practice_27

import android.support.v7.app.AppCompatActivity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.parth.kotlin_practice_27.databinding.ActivityMainBinding
import com.example.parth.kotlinpractice_2.support.CoreActivity
import com.support.kotlin.setUpNavigationDrawer
import kotlinx.android.synthetic.main.activity_main.*
import android.view.ViewAnimationUtils
import android.animation.Animator
import android.opengl.ETC1.getHeight
import android.opengl.ETC1.getWidth
import android.view.View
import android.widget.Toast


class MainActivity : CoreActivity<MainActivity,ActivityMainBinding,MainViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setDefaults(this, R.layout.activity_main)
    }

    override fun setVM(binding: ActivityMainBinding) {
        binding.vm = viewModel
    }

    override fun createViewModel(activity: MainActivity): MainViewModel = MainViewModel(activity)

    override fun getActionBarTitle(): String = "DashBoard"

    override fun workArea(viewModel: MainViewModel) {
//        viewModel.createList()
    }

    override fun isBackEnabled(): Boolean? = false

    companion object {
        fun getIntent(context: Context): Intent {
            var intent = Intent(context, MainActivity::class.java)
            return intent
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Runtime.getRuntime().gc()
    }

//    override fun onBackPressed() {
//        super.onBackPressed()
//    }

}
