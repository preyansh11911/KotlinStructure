package com.example.parth.commenthierarchydemo.comments_module

import android.view.ContextMenu
import android.view.View
import com.bignerdranch.expandablerecyclerview.ParentViewHolder
import kotlinx.android.synthetic.main.parent_single_item.view.*

class CommentViewHolder(itemView: View) : ParentViewHolder<Comment, Reply>(itemView), View.OnCreateContextMenuListener {

    val txtName = itemView.txt_cmnt_name
    val txtComment = itemView.txt_cmnt
    val txtLike = itemView.txt_cmnt_like
    val txtReply = itemView.txt_cmnt_reply
    val llReplyBox = itemView.ll_cmnt_rpl_box
    val txtCommentReplyName = itemView.txt_cmnt_rpl_name
    val txtCommentReplyMessage = itemView.txt_cmnt_rpl_msg
    val contextMenuListener = this

//    fun bind(activity: MainActivity, comment: Comment) {
//        itemView.setOnCreateContextMenuListener(contextMenuListener)
//        txtName.text = comment.name
//        txtComment.text = comment.cmntMsg
//        txtReply.setOnClickListener {
//            activity.ed_comment.requestFocus()
//            activity.showSoftKeyboard()
//            activity.type = CommentType.REPLY
////            activity.itemPosition = parentAdapterPosition
//        }
//        if (comment.replyList!!.isEmpty()) llReplyBox.visibility = View.GONE
//        else {
//            if (!isExpanded) {
//                llReplyBox.visibility = View.VISIBLE
//                txtCommentReplyName.text = comment.replyList!![0].name
//                txtCommentReplyMessage.text = comment.replyList!![0].rplMsg
//            }
//        }
//        llReplyBox.setOnClickListener {
//            if (!isExpanded) {
//                expandView()
//                llReplyBox.visibility = View.GONE
//            }
//        }
//    }

    fun bind() {
        itemView.setOnCreateContextMenuListener(contextMenuListener)
    }

    fun expand() {
        expandView()
    }

    override fun shouldItemViewClickToggleExpansion() = false

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        menu?.add(CommentType.COMMENT, v!!.id, parentAdapterPosition, "Edit")
        menu?.add(CommentType.COMMENT, v!!.id, parentAdapterPosition, "Delete")
    }
}