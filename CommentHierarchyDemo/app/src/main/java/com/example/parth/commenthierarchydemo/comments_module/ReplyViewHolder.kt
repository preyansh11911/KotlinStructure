package com.example.parth.commenthierarchydemo.comments_module

import android.view.ContextMenu
import android.view.View
import com.bignerdranch.expandablerecyclerview.ChildViewHolder
import kotlinx.android.synthetic.main.child_single_item.view.*

class ReplyViewHolder(itemView: View) : ChildViewHolder<Reply>(itemView), View.OnCreateContextMenuListener {

    val txtName = itemView.txt_rpl_name
    val txtReply = itemView.txt_rpl
    val txtLike = itemView.txt_rpl_like
    val txtRplReply = itemView.txt_rpl_reply
    val contextMenuListener = this

//    fun bind(activity: MainActivity, reply: Reply) {
//        txtName.text = reply.name
//        txtReply.text = reply.rplMsg
//        txtLike.setOnClickListener { Toast.makeText(itemView.context, "You liked this reply.", Toast.LENGTH_SHORT).show() }
//        txtRplReply.setOnClickListener {
//            activity.ed_comment.requestFocus()
//            activity.showSoftKeyboard()
//            activity.type = CommentType.REPLY
////            activity.itemPosition = parentAdapterPosition
//        }
//    }

    fun bind() {
        itemView.setOnCreateContextMenuListener(contextMenuListener)
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        menu?.add(CommentType.REPLY, v!!.id, parentAdapterPosition, "Edit")
        menu?.add(CommentType.REPLY, v!!.id, parentAdapterPosition, "Delete")
    }
}