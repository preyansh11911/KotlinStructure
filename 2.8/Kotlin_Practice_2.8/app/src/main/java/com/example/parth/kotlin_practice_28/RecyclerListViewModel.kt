package com.example.parth.kotlin_practice_28

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.support.FragmentViewModel
import com.support.setUp
import kotlinx.android.synthetic.main.fragment_list.view.*
import kotlinx.android.synthetic.main.item_row_main.view.*

class RecyclerListViewModel(val mFragment: RecyclerListFragment) : FragmentViewModel() {

    lateinit var arrList: ArrayList<SampleModel>

    val binding = mFragment.binding
    val view: View = binding.root

    fun createVerticalList() {
        createList(LinearLayoutManager(view.rec_vertical.context, LinearLayoutManager.VERTICAL, false))
    }

    fun createHorizontalList() {
        createList(LinearLayoutManager(view.rec_vertical.context, LinearLayoutManager.HORIZONTAL, false))
    }

    fun createList(layoutManager: LinearLayoutManager) {
        view.rec_horizontal.setUp(R.layout.item_row_horizontal, createList()) {
            setLayoutManager(layoutManager)
            contentBinder { item, view ->
                view.tv_name.text = item.name
                view.tv_desc.text = item.desc
            }
            setNestedScrollingEnabled(false)
        }
    }

//    fun createGridList() {
//        view.rec_horizontal.setUp(R.layout.item_row_horizontal, createList()) {
//            setLayoutManager(GridLayoutManager())
//            contentBinder { item, view ->
//                view.tv_name.text = item.name
//                view.tv_desc.text = item.desc
//            }
//            setNestedScrollingEnabled(false)
//        }
//    }

    private fun createList(): ArrayList<SampleModel> {
        arrList = ArrayList()
        for (i in 1..30) {
            arrList.add(SampleModel("Name : ", "Age : 25"))
        }
        return arrList
    }

}