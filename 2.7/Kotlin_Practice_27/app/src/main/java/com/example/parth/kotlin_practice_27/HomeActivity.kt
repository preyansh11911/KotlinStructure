package com.example.parth.kotlin_practice_27

import android.support.v7.app.AppCompatActivity
import android.content.Context
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import android.view.ViewAnimationUtils
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_main.*
import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.opengl.ETC1.getHeight
import android.opengl.ETC1.getWidth
import android.view.MotionEvent
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import com.example.parth.kotlinpractice_2.support.kotlin.getColor


class HomeActivity : AppCompatActivity() {

    var currentSelected = 0

    companion object {
        fun getIntent(context: Context): Intent {
            var intent = Intent(context, HomeActivity::class.java)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        btn_next.setOnClickListener {
            startActivity(MainActivity.getIntent(this@HomeActivity))
//            val coOrdis : IntArray = IntArray(2)
//            it.getLocationInWindow(coOrdis)
//
//            val x = coOrdis[0]
//            val y = coOrdis[1]
//
//            val startRadius = 10
//            val endRadius = Math.hypot((home_content_layout.width).toDouble(), (home_content_layout.height).toDouble()).toInt()
//
//            val anim = ViewAnimationUtils.createCircularReveal(home_content_layout, x, y, startRadius.toFloat(), endRadius.toFloat()).apply { duration = 1000 }
//
//            layout_app_exit.setVisibility(View.VISIBLE)
//            anim.start()
        }

//        home_content_layout.setOnClickListener {
//            reveal(MotionEvent.obtain())
//        }

//        home_content_layout.setOnTouchListener { v, event ->
//            if (event.action === MotionEvent.ACTION_DOWN) {
//                reveal(event)
//                return@setOnTouchListener true
//            } else {
//                return@setOnTouchListener false
//            }
//        }
    }

    private fun reveal(me: MotionEvent) {
        layout_app_exit.setVisibility(VISIBLE)
        val cx = layout_app_exit.getWidth()
        val cy = layout_app_exit.getHeight()

        val finalRadius = Math.max(cx, cy).toFloat() * 1.2f
        val animation = ViewAnimationUtils
                .createCircularReveal(layout_app_exit, me.x.toInt(), me.y.toInt(), 0f, finalRadius).apply { duration = 500 }

        animation.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animator: Animator) {
                if (currentSelected === 0) {
                    home_content_layout.setBackgroundColor(R.color.colorAccent.getColor(this@HomeActivity))
                    currentSelected = 1
                } else {
                    home_content_layout.setBackgroundColor(R.color.colorPrimary.getColor(this@HomeActivity))
                    currentSelected = 0
                }
                layout_app_exit.setVisibility(INVISIBLE)
            }
        })

        if (currentSelected === 0) {
            layout_app_exit.setBackgroundColor(R.color.colorAccent.getColor(this@HomeActivity))
        } else {
            layout_app_exit.setBackgroundColor(R.color.colorPrimary.getColor(this@HomeActivity))
        }
        animation.start()
    }


    override fun onBackPressed() {

        val x = 0
        val y = trans_layout.height

        val startRadius = 0
        val endRadius = Math.hypot((trans_layout.width).toDouble(), (trans_layout.height).toDouble()).toInt()
        val anim2StartRadius = Math.hypot((layout_app_exit.width).toDouble(), (layout_app_exit.height).toDouble()).toInt()

//        val anim = ViewAnimationUtils.createCircularReveal(layout_app_exit, x, y, startRadius.toFloat(), endRadius.toFloat()).apply { duration = 1000 }
        val anim2 = ViewAnimationUtils.createCircularReveal(trans_layout, x, y, startRadius.toFloat(), endRadius.toFloat()).apply { duration = 500 }

//        anim.addListener(object : AnimatorListenerAdapter() {
//            override fun onAnimationEnd(animation: Animator?) {
//            }
//        })
        trans_layout.visibility = VISIBLE
        anim2.start()

//        layout_app_exit.visibility = VISIBLE
//        anim.start()
    }

}
