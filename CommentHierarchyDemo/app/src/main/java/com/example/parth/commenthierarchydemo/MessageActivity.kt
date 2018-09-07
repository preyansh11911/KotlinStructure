package com.example.parth.commenthierarchydemo

import android.os.Bundle
import android.util.Log
import android.view.Menu
import com.example.parth.commenthierarchydemo.databinding.ActivityMessageBinding
import com.example.parth.kotlinpractice_2.support.CoreActivity
import com.support.builders.ApiBuilder.*
import com.support.builders.ApiBuilder.WebServices.ApiNames.movieList
import kotlinx.android.synthetic.main.activity_message.*

class MessageActivity : CoreActivity<MessageActivity, ActivityMessageBinding, MessageViewModel>(), SingleCallback {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setDefaults(this, R.layout.activity_message)
    }

    override fun setVM(binding: ActivityMessageBinding) {
        binding.vm = vm
    }

    override fun createViewModel(activity: MessageActivity) = MessageViewModel(this)

    override fun getActionBarTitle() = "Message"

    override fun workArea(vm: MessageViewModel) {
        vm.setBaseURL()
//        vm.createMessageList(rec_msg)
//        vm.callMoviesApi()
        callApi(movieList, this)
        { ApiBuilder.webServices!!.movieList() }
    }

    override fun isBackEnabled() = false

    override fun isCustomActionbar() = true

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.sample_menu, menu)
        return true
    }

    override fun onSuccess(o: Any, apiName: WebServices.ApiNames) {
        vm!!.movieList = o as ArrayList<MoviesItem>
        vm!!.createMessageList(rec_msg)
    }

    override fun onFailure(throwable: Throwable, apiName: WebServices.ApiNames) {
        Log.e("MessageActivity", "Failure")
    }
}
