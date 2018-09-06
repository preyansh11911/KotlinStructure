package com.example.parth.commenthierarchydemo

import android.os.Bundle
import android.util.Log
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

    override fun onSuccess(o: Any, apiName: WebServices.ApiNames) {
        vm!!.movieList = o as ArrayList<MoviesItem>
        vm!!.createMessageList(rec_msg)
    }

    override fun onFailure(throwable: Throwable, apiName: WebServices.ApiNames) {
        Log.e("MessageActivity", "Failure")
    }

    override fun onTokenExpire(apiName: WebServices.ApiNames) {
        Log.e("MessageActivity", "TokenExpired")
    }


}
