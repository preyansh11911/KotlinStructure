package com.example.parth.commenthierarchydemo.comments_module

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bignerdranch.expandablerecyclerview.ExpandableRecyclerAdapter
import com.example.parth.commenthierarchydemo.R

class CommentListAdapter(context: Context, commentList: MutableList<Comment>) : ExpandableRecyclerAdapter<Comment,Reply,CommentViewHolder, ReplyViewHolder>(commentList) {

    val mInflater = LayoutInflater.from(context)

    override fun onCreateParentViewHolder(parentViewGroup: ViewGroup, viewType: Int): CommentViewHolder {
        val commentView = mInflater.inflate(R.layout.parent_single_item, parentViewGroup, false)
        return CommentViewHolder(commentView)
    }

    override fun onCreateChildViewHolder(childViewGroup: ViewGroup, viewType: Int): ReplyViewHolder {
        val replyView = mInflater.inflate(R.layout.child_single_item,childViewGroup,false)
        return ReplyViewHolder(replyView)
    }

    override fun onBindParentViewHolder(commentHolder: CommentViewHolder, parentPosition: Int, comment: Comment) {
        commentHolder.bind(comment)
    }

    override fun onBindChildViewHolder(replyHolder: ReplyViewHolder, parentPosition: Int, childPosition: Int, reply: Reply) {
        replyHolder.bind(reply)
    }
}