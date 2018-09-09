package com.preyansh.customactionbardemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.sample_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
}
