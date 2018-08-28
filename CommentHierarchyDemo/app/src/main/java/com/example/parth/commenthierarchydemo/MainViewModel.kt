package com.example.parth.commenthierarchydemo

import android.support.v7.widget.LinearLayoutManager
import com.example.parth.commenthierarchydemo.MainViewModel.Type.COMMENT
import com.example.parth.commenthierarchydemo.comments_module.Comment
import com.example.parth.commenthierarchydemo.comments_module.CommentListAdapter
import com.example.parth.commenthierarchydemo.comments_module.Reply
import com.example.parth.kotlinpractice_2.support.ActivityViewModel
import com.support.kotlin.hideSoftKeyboard
import kotlinx.android.synthetic.main.activity_main.*

class MainViewModel(val mActivity: MainActivity) : ActivityViewModel(mActivity) {

    val commentList = ArrayList<Comment>()
    val commentListAdapter = CommentListAdapter(mActivity, commentList)

    fun createDRCList() {
        mActivity.rec_view.adapter = commentListAdapter
        mActivity.rec_view.layoutManager = LinearLayoutManager(mActivity)
    }

    fun addItemToList(commentText: String, type: Type, position: Int) {
        if (type == COMMENT) {
            commentListAdapter.addItem(Comment("User : " + commentList.size, commentText, ArrayList<Reply>()))
            commentListAdapter.scroll()
        } else {
            commentListAdapter.addItem(commentText, position)
//            commentListAdapter.expandParent(position)
        }
        mActivity.hideSoftKeyboard()
    }

    enum class Type {
        COMMENT, REPLY
    }
}