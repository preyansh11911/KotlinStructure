package com.example.parth.commenthierarchydemo

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import com.example.parth.commenthierarchydemo.MainViewModel.Type.COMMENT
import com.example.parth.commenthierarchydemo.comments_module.Comment
import com.example.parth.commenthierarchydemo.comments_module.CommentListAdapter
import com.example.parth.commenthierarchydemo.comments_module.Reply
import com.example.parth.kotlinpractice_2.support.ActivityViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.support.kotlin.hideSoftKeyboard
import kotlinx.android.synthetic.main.activity_main.*

class MainViewModel(val mActivity: MainActivity) : ActivityViewModel(mActivity) {

    var commentList = ArrayList<Comment>()
    lateinit var commentListAdapter: CommentListAdapter

    fun createDRCList() {

        val cmntListString = sharedPref.getString("COMMENT_ARRAY", "")
        if (cmntListString.isNotBlank()) {
            commentList = Gson().fromJson(cmntListString, object : TypeToken<ArrayList<Comment>>() {}.type)
        }

        commentListAdapter = CommentListAdapter(mActivity, commentList)
        mActivity.rec_view.adapter = commentListAdapter
        val llm = LinearLayoutManager(mActivity)
//        llm.reverseLayout = true
        llm.stackFromEnd = true
        mActivity.rec_view.layoutManager = llm
    }

    fun addItemToList(commentText: String, type: Type, position: Int) {
        if (type == COMMENT) {
            commentListAdapter.addItem(Comment("User : " + commentList.size, commentText, ArrayList<Reply>()))
            commentListAdapter.scroll()
        } else {
            commentListAdapter.addItem(commentText, position)
        }
        mActivity.hideSoftKeyboard()
    }

    enum class Type {
        COMMENT, REPLY
    }

    val sharedPref = activity.getSharedPreferences("Comment_Preferences", Context.MODE_PRIVATE)
}