package com.example.parth.commenthierarchydemo

import android.support.v7.widget.RecyclerView
import com.example.parth.commenthierarchydemo.databinding.SampleSingleItemBinding
import com.example.parth.kotlinpractice_2.support.ActivityViewModel
import com.support.builders.RecyckerViewBuilder.RecyclerViewLayoutManager.LINEAR
import com.support.builders.RecyckerViewBuilder.RecyclerViewLinearLayout.VERTICAL
import com.support.builders.RecyckerViewBuilder.setUpWithBinding

class MessageViewModel(mActivity: MessageActivity) : ActivityViewModel(mActivity) {
    fun createMessageList(rec_msg: RecyclerView) {
//        rec_msg.setUp(R.layout.sample_single_item,makeArrList(), LINEAR, VERTICAL) {
//            contentBinder { sample, view ->
//                view.txt_one.text = sample.title
//                view.txt_desc.text = sample.desc
//            }
//        }

        rec_msg.setUpWithBinding<Sample, SampleSingleItemBinding>(
                R.layout.sample_single_item,
                makeArrList(),
                LINEAR,
                VERTICAL) {
            contentBinder { item, binding, position ->

            }
        }
    }

    private fun makeArrList(): ArrayList<Sample> {
        val arrList = ArrayList<Sample>()
        for (i in 0..29)
            arrList.add(Sample("Title", "This is a description text."))
        return arrList
    }
}