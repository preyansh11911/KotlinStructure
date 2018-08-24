package com.example.parth.commenthierarchydemo

import android.support.v7.widget.LinearLayoutManager
import com.example.parth.commenthierarchydemo.comments_module.Comment
import com.example.parth.commenthierarchydemo.comments_module.CommentListAdapter
import com.example.parth.commenthierarchydemo.comments_module.Reply
import com.example.parth.kotlinpractice_2.support.ActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainViewModel(val mActivity: MainActivity) : ActivityViewModel(mActivity) {

    fun createDRCList() {
        mActivity.rec_view.setAdapter(CommentListAdapter(mActivity,createList()));
        mActivity.rec_view.setLayoutManager(LinearLayoutManager(mActivity));
    }

    private fun createList() : MutableList<Comment> {

        val rpl1 = Reply("Reply User1 Reply User1 Reply User1 Reply User1 Reply User1 Reply User1 Reply User1 ","This is a reply from User 1 on the comment above. from User 1 on the comment above. from User 1 on the comment above. from User 1 on the comment above. from User 1 on the comment above. from User 1 on the comment above. from User 1 on the comment above. from User 1 on the comment above. from User 1 on the comment above. from User 1 on the comment above. from User 1 on the comment above. from User 1 on the comment above. from User 1 on the comment above. from User 1 on the comment above. from User 1 on the comment above. from User 1 on the comment above. from User 1 on the comment above. from User 1 on the comment above. from User 1 on the comment above. from User 1 on the comment above. from User 1 on the comment above. from User 1 on the comment above. from User 1 on the comment above. from User 1 on the comment above. from User 1 on the comment above. from User 1 on the comment above. from User 1 on the comment above. from User 1 on the comment above. from User 1 on the comment above. ")
        val rpl2 = Reply("Reply User2","This is a reply from User 2 on the comment above.")
        val rpl3 = Reply("Reply User3","This is a reply from User 3 on the comment above.")
        val rpl4 = Reply("Reply User4","This is a reply from User 4 on the comment above.")
        val rpl5 = Reply("Reply User5","This is a reply from User 5 on the comment above.")
        val rpl6 = Reply("Reply User6","This is a reply from User 6 on the comment above.")
        val rpl7 = Reply("Reply User7","This is a reply from User 7 on the comment above.")
        val rpl8 = Reply("Reply User8","This is a reply from User 8 on the comment above.")
        val rpl9 = Reply("Reply User9","This is a reply from User 9 on the comment above.")
        val rpl10 = Reply("Reply User10","This is a reply from User 10 on the comment above.")
        val rpl11 = Reply("Reply User11","This is a reply from User 11 on the comment above.")
        val rpl12 = Reply("Reply User12","This is a reply from User 12 on the comment above.")
        val rpl13 = Reply("Reply User13","This is a reply from User 13 on the comment above.")
        val rpl14 = Reply("Reply User14","This is a reply from User 14 on the comment above.")

        val cmnt1 = Comment("Comment User1 Comment User1 Comment User1 Comment User1 Comment User1 Comment User1 Comment User1 Comment User1 Comment User1 ","Here, User1 is commeHere, User1 is commeHere, User1 is commeHere, User1 is commeHere, User1 is commeHere, User1 is commeHere, User1 is commeHere, User1 is commeHere, User1 is commeHere, User1 is commeHere, User1 is commenting the post I am looking at. Here, User1 is commenting the post I am looking at. Here, User1 is commenting the post I am looking at. Here, User1 is commenting the post I am looking at. Here, User1 is commenting the post I am looking at. Here, User1 is commenting the post I am looking at. Here, User1 is commenting the post I am looking at. ", Arrays.asList(rpl8,rpl10,rpl12))
        val cmnt2 = Comment("Comment User2","Here, User2 is commenting the post I am looking at.", Arrays.asList())
        val cmnt3 = Comment("Comment User3","Here, User3 is commenting the post I am looking at.", Arrays.asList(rpl9,rpl11,rpl14))
        val cmnt4 = Comment("Comment User4","Here, User4 is commenting the post I am looking at.", Arrays.asList())
        val cmnt5 = Comment("Comment User5","Here, User5 is commenting the post I am looking at.", Arrays.asList(rpl1))
        val cmnt6 = Comment("Comment User6","Here, User6 is commenting the post I am looking at.", Arrays.asList(rpl3,rpl4,rpl8,rpl7,rpl13))
        val cmnt7 = Comment("Comment User7","Here, User7 is commenting the post I am looking at.", Arrays.asList(rpl8,rpl10,rpl6))

        return Arrays.asList(cmnt1,cmnt2,cmnt3,cmnt4,cmnt5,cmnt6,cmnt7)
    }

}