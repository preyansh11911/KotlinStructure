package com.example.parth.commenthierarchydemo.comments_module

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bignerdranch.expandablerecyclerview.ExpandableRecyclerAdapter
import com.example.parth.commenthierarchydemo.MainActivity
import com.example.parth.commenthierarchydemo.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*

class CommentListAdapter(val activity: MainActivity, val commentList: ArrayList<Comment>) : ExpandableRecyclerAdapter<Comment, Reply, CommentViewHolder, ReplyViewHolder>(commentList) {

    val mInflater = LayoutInflater.from(activity)

    override fun onCreateParentViewHolder(parentViewGroup: ViewGroup, viewType: Int): CommentViewHolder {
        val commentView = mInflater.inflate(R.layout.parent_single_item, parentViewGroup, false)
        return CommentViewHolder(commentView)
    }

    override fun onCreateChildViewHolder(childViewGroup: ViewGroup, viewType: Int): ReplyViewHolder {
        val replyView = mInflater.inflate(R.layout.child_single_item, childViewGroup, false)
        return ReplyViewHolder(replyView)
    }

    override fun onBindParentViewHolder(commentHolder: CommentViewHolder, parentPosition: Int, comment: Comment) {
        commentHolder.bind(activity, comment)
    }

    override fun onBindChildViewHolder(replyHolder: ReplyViewHolder, parentPosition: Int, childPosition: Int, reply: Reply) {
        replyHolder.bind(activity, reply)
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