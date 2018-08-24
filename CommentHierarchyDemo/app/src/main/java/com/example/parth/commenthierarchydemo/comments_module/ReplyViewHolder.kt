package com.example.parth.commenthierarchydemo.comments_module

import android.view.View
import android.widget.Toast
import com.bignerdranch.expandablerecyclerview.ChildViewHolder
import kotlinx.android.synthetic.main.child_single_item.view.*

class ReplyViewHolder(itemView: View) : ChildViewHolder<Reply>(itemView) {

    val txtName = itemView.txt_rpl_name
    val txtReply = itemView.txt_rpl
    val txtLike = itemView.txt_rpl_like
    val txtRplReply = itemView.txt_rpl_reply

    fun bind(reply: Reply) {
        txtName.text = reply.name
        txtReply.text = reply.rplMsg
        txtLike.setOnClickListener { Toast.makeText(itemView.context, "You liked this reply.", Toast.LENGTH_SHORT).show() }
        txtRplReply.setOnClickListener { Toast.makeText(itemView.context, "Work In Progress", Toast.LENGTH_SHORT).show() }
    }
}