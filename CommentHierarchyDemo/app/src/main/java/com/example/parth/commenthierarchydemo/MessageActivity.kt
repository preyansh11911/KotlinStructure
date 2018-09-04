package com.example.parth.commenthierarchydemo

import android.os.Bundle
import com.example.parth.commenthierarchydemo.databinding.ActivityMessageBinding
import com.example.parth.kotlinpractice_2.support.CoreActivity
import kotlinx.android.synthetic.main.activity_message.*

class MessageActivity : CoreActivity<MessageActivity, ActivityMessageBinding, MessageViewModel>() {
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
        vm.createMessageList(rec_msg)
    }
}
