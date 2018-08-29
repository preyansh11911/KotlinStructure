package com.example.parth.commenthierarchydemo.comments_module

import android.view.View
import android.widget.Toast
import com.bignerdranch.expandablerecyclerview.ChildViewHolder
import com.example.parth.commenthierarchydemo.MainActivity
import com.example.parth.commenthierarchydemo.MainViewModel
import com.support.kotlin.showSoftKeyboard
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.child_single_item.view.*

class ReplyViewHolder(itemView: View) : ChildViewHolder<Reply>(itemView) {

    val txtName = itemView.txt_rpl_name
    val txtReply = itemView.txt_rpl
    val txtLike = itemView.txt_rpl_like
    val txtRplReply = itemView.txt_rpl_reply

    fun bind(activity: MainActivity, reply: Reply) {
        txtName.text = reply.name
        txtReply.text = reply.rplMsg
        txtLike.setOnClickListener { Toast.makeText(itemView.context, "You liked this reply.", Toast.LENGTH_SHORT).show() }
        txtRplReply.setOnClickListener {
            activity.ed_comment.requestFocus()
            activity.showSoftKeyboard()
            activity.type = MainViewModel.Type.REPLY
            activity.itemPosition = parentAdapterPosition
        }
    }
}