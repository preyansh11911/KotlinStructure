package com.example.parth.commenthierarchydemo.comments_module

import com.bignerdranch.expandablerecyclerview.model.Parent

data class Comment(var name: String, var cmntMsg: String, var replyList: ArrayList<Reply>?) : Parent<Reply> {
    override fun getChildList() = replyList

    override fun isInitiallyExpanded() = false
}