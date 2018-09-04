package com.example.parth.commenthierarchydemo.comments_module

import android.view.View
import android.widget.Toast
import com.bignerdranch.expandablerecyclerview.ParentViewHolder
import com.example.parth.commenthierarchydemo.MainActivity
import com.example.parth.commenthierarchydemo.MainViewModel
import com.support.kotlin.showSoftKeyboard
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.parent_single_item.view.*

class CommentViewHolder(itemView: View) : ParentViewHolder<Comment, Reply>(itemView) {
    val txtName = itemView.txt_cmnt_name
    val txtComment = itemView.txt_cmnt
    val txtLike = itemView.txt_cmnt_like
    val txtReply = itemView.txt_cmnt_reply
    val llReplyBox = itemView.ll_cmnt_rpl_box
    val txtCommentReplyName = itemView.txt_cmnt_rpl_name
    val txtCommentReplyMessage = itemView.txt_cmnt_rpl_msg

    fun bind(activity: MainActivity, comment: Comment) {
        txtName.text = comment.name
        txtComment.text = comment.cmntMsg
        txtLike.setOnClickListener { Toast.makeText(itemView.context, "You liked this comment.", Toast.LENGTH_SHORT).show() }
        txtReply.setOnClickListener {
            activity.ed_comment.requestFocus()
            activity.showSoftKeyboard()
            activity.type = MainViewModel.Type.REPLY
            activity.itemPosition = parentAdapterPosition
        }
        if (comment.replyList!!.isEmpty()) llReplyBox.visibility = View.GONE
        else {
            if (!isExpanded) {
                llReplyBox.visibility = View.VISIBLE
                txtCommentReplyName.text = comment.replyList!![0].name
                txtCommentReplyMessage.text = comment.replyList!![0].rplMsg
            }
        }
        llReplyBox.setOnClickListener {
            if (!isExpanded) {
                expandView()
                llReplyBox.visibility = View.GONE
            }
        }
    }

    override fun shouldItemViewClickToggleExpansion() = false
}