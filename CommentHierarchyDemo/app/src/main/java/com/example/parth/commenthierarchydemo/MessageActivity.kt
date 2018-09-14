package com.example.parth.commenthierarchydemo

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Toast
import com.example.parth.commenthierarchydemo.databinding.ActivityMessageBinding
import com.example.parth.kotlinpractice_2.support.CoreActivity
import com.support.builders.ApiBuilder.*
import com.support.builders.ApiBuilder.WebServices.ApiNames.registration
import java.util.*

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
        callApi(registration, this, getHeader())
        {
            ApiBuilder.webServices!!.registerUser(
                    "Preyansh",
                    "PB",
                    "123456789012",
                    "presh1236@hmail.com",
                    "presh@123",
                    "1111111",
                    "67898765434"
            )
        }
    }

    fun getHeader(): MutableList<Header> {
        return Arrays.asList(Header("Content-Type", "application/x-www-form-urlencoded"))
    }

    override fun isBackEnabled() = false

    override fun onSuccess(o: Any, apiName: WebServices.ApiNames) {
//        vm!!.movieList = o as ArrayList<MoviesItem>
//        vm!!.createMessageList(rec_msg)
        Toast.makeText(this@MessageActivity, o.toString(), Toast.LENGTH_SHORT).show()
    }

    override fun onFailure(throwable: Throwable, apiName: WebServices.ApiNames) {
        Log.e("MessageActivity", "Failure")
    }

    override fun isCustomActionbar(): Boolean {
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.sample_menu, menu)
        return true
    }
}
