package com.example.parth.commenthierarchydemo.comments_module

import android.view.View
import android.widget.Toast
import com.bignerdranch.expandablerecyclerview.ParentViewHolder
import kotlinx.android.synthetic.main.parent_single_item.view.*

class CommentViewHolder(itemView: View) : ParentViewHolder<Comment, Reply>(itemView) {
    val txtName = itemView.txt_cmnt_name
    val txtComment = itemView.txt_cmnt
    val txtLike = itemView.txt_cmnt_like
    val txtReply = itemView.txt_cmnt_reply
    val txtViewReply = itemView.txt_cmnt_view_replies

    fun bind(comment: Comment) {
        txtName.text = comment.name
        txtComment.text = comment.cmntMsg
        txtLike.setOnClickListener { Toast.makeText(itemView.context, "You liked this comment.", Toast.LENGTH_SHORT).show() }
        txtReply.setOnClickListener { Toast.makeText(itemView.context, "Work In Progress", Toast.LENGTH_SHORT).show() }
        if (comment.replyList!!.isEmpty()) txtViewReply.visibility = View.GONE else txtViewReply.visibility = View.VISIBLE
        txtViewReply.setOnClickListener {
            if (isExpanded) {
                txtViewReply.text = "View replies"
                collapseView()
            }
            else {
                txtViewReply.text = "Hide replies"
                expandView()
            }
        }
    }
}