package com.example.parth.commenthierarchydemo.comments_module

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bignerdranch.expandablerecyclerview.ExpandableRecyclerAdapter
import com.example.parth.commenthierarchydemo.MainActivity
import com.example.parth.commenthierarchydemo.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.support.kotlin.showSoftKeyboard
import kotlinx.android.synthetic.main.activity_main.*

class CommentListAdapter(val activity: MainActivity, val commentList: ArrayList<Comment>) : ExpandableRecyclerAdapter<Comment, Reply, CommentViewHolder, ReplyViewHolder>(commentList) {

    var parentPosition: Int = -1
    var childPosition: Int = -1
    val mInflater = LayoutInflater.from(activity)
//    val contextMenuListener = this

//    override fun getItemCount(): Int = commentList.size
//
//    override fun getItemId(position: Int): Long {
//        return commentList[position].hashCode().toLong()
//    }

    override fun onCreateParentViewHolder(parentViewGroup: ViewGroup, viewType: Int): CommentViewHolder {
        val commentView = mInflater.inflate(R.layout.parent_single_item, parentViewGroup, false)
        return CommentViewHolder(commentView)
    }

    override fun onCreateChildViewHolder(childViewGroup: ViewGroup, viewType: Int): ReplyViewHolder {
        val replyView = mInflater.inflate(R.layout.child_single_item, childViewGroup, false)
        return ReplyViewHolder(replyView)
    }

    override fun onBindParentViewHolder(commentHolder: CommentViewHolder, parentPosition: Int, comment: Comment) {
        commentHolder.bind()
//        commentHolder.itemView.setOnCreateContextMenuListener(contextMenuListener)
        commentHolder.txtName.text = comment.name
        commentHolder.txtComment.text = comment.cmntMsg
        commentHolder.txtReply.setOnClickListener {
            this.parentPosition = parentPosition
            activity.ed_comment.requestFocus()
            activity.showSoftKeyboard()
            activity.type = CommentType.REPLY
        }
        if (comment.replyList!!.isEmpty()) commentHolder.llReplyBox.visibility = View.GONE
        else {
            if (!commentHolder.isExpanded) {
                commentHolder.llReplyBox.visibility = View.VISIBLE
                commentHolder.txtCommentReplyName.text = comment.replyList!![0].name
                commentHolder.txtCommentReplyMessage.text = comment.replyList!![0].rplMsg
            }
        }
        commentHolder.llReplyBox.setOnClickListener {
            if (!commentHolder.isExpanded) {
                this.parentPosition = parentPosition
                commentHolder.expand()
                commentHolder.llReplyBox.visibility = View.GONE
            }
        }
        commentHolder.txtLike.setOnClickListener { Toast.makeText(activity, "You liked this comment.", Toast.LENGTH_SHORT).show() }
        commentHolder.itemView.setOnLongClickListener {
            this.parentPosition = parentPosition
            return@setOnLongClickListener false
        }
    }

    override fun onBindChildViewHolder(replyHolder: ReplyViewHolder, parentPosition: Int, childPosition: Int, reply: Reply) {
//        replyHolder.bind(activity, reply)
        replyHolder.bind()
        replyHolder.txtName.text = reply.name
        replyHolder.txtReply.text = reply.rplMsg
        replyHolder.txtLike.setOnClickListener { Toast.makeText(replyHolder.itemView.context, "You liked this reply.", Toast.LENGTH_SHORT).show() }
        replyHolder.txtRplReply.setOnClickListener {
            activity.ed_comment.requestFocus()
            activity.showSoftKeyboard()
            activity.type = CommentType.REPLY
            this.parentPosition = parentPosition
            this.childPosition = childPosition
//            activity.itemPosition = parentAdapterPosition
        }
        replyHolder.itemView.setOnLongClickListener {
            this.parentPosition = parentPosition
            this.childPosition = childPosition
            return@setOnLongClickListener false
        }
    }

    fun addItem(comment: Comment) {
        commentList.add(comment)
        addListToPref()
        notifyParentInserted(commentList.size - 1)
    }

    fun addItem(reply: String, position: Int) {
        val comment = commentList[position]
        comment.replyList?.add(Reply("Reply : " + comment.replyList?.size, reply))
        addListToPref()
        if (comment.replyList!!.size == 1)
            notifyParentDataSetChanged(true)
        else
            notifyChildInserted(position, comment.replyList!!.size - 1)
    }

    fun removeComment(position: Int) {
        commentList.removeAt(position)
        notifyParentDataSetChanged(false)
        addListToPref()
    }

    fun removeReply(parentPosition: Int, childPosition: Int) {
        val comment = commentList[parentPosition]
        comment.replyList?.removeAt(childPosition)
        notifyParentDataSetChanged(false)
        addListToPref()
    }

    fun addListToPref() {
        val commentListString = Gson().toJson(commentList, object : TypeToken<ArrayList<Comment>>() {}.type)
        with(activity.vm!!.sharedPref.edit()) {
            putString("COMMENT_ARRAY", commentListString)
            apply()
        }
    }

    fun scroll() {
        activity.rec_view.scrollToPosition(commentList.size - 1)
    }
}