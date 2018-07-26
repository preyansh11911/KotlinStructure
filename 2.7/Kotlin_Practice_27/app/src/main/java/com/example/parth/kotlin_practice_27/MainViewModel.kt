package com.example.parth.kotlin_practice_27

import android.os.Handler
import android.util.Log
import android.widget.Toast
import com.example.parth.kotlin_practice_27.databinding.RowItemBinding
import com.example.parth.kotlinpractice_2.support.ActivityViewModel
import com.support.kotlin.setUpRecyclerView
import com.support.kotlin.setUpRecyclerView_Binding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.row_item.view.*
import java.util.Collections.addAll

class MainViewModel(val mActivity: MainActivity) : ActivityViewModel(mActivity) {

    lateinit var arrList: ArrayList<SampleModel>

    init {

        /**
         * <p>
         * The following method is to show
         * how to create a recycler view with databinding.
         * </p>
         */

//        mActivity.recView.setUpRecyclerView_Binding<SampleModel, RowItemBinding>(createList(),
//                builder = {
//                    itemView = R.layout.row_item
//                    contentBinder { item, binding, position ->
//                        binding.setOnItemClickListener {
//                            Toast.makeText(mActivity, "Name : ${item.name}", Toast.LENGTH_SHORT).show()
//                            remove(item)
//                        }
//                    }
//                    enableLoadMore {
//                        showLoader()
//                        loadMoreData { hasMore, list ->
//                            removeLastItem()
//                            stopLoader()
//                            this.hasMore = hasMore
//                            if (hasMore) addAll(list!!)
//                        }
//                    }
//                })

        mActivity.recView.setUpRecyclerView(createList()) {
            itemView = R.layout.row_item
            contentBinder { item, v ->
                v.txt_item_name.text = "Name : ${item.name}"
                v.txt_item_age.text = "Age : ${item.age}"
                v.setOnClickListener {
                    Toast.makeText(mActivity, "Item : ${item.name}", Toast.LENGTH_SHORT).show()
                }
                v.img_btn_delete.setOnClickListener {
                    removeItem(item)
                }
            }

            enableLoadMore {
                showLoader()
                loadMoreData { hasMore, list ->
                    removeLastItem()
                    stopLoader()
                    this.hasMore = hasMore
                    if (hasMore) addAll(list!!)
                }
            }
        }

        mActivity.swipeRefresh.setOnRefreshListener {
            val sModel = SampleModel(name = "User Name ", age = 27)
            sModel.id = ((arrList.size) + 2).toLong()
            arrList.add(0, sModel)
            mActivity.recView.adapter.notifyDataSetChanged()
            mActivity.swipeRefresh.isRefreshing = false
        }
    }

    private fun createList(): ArrayList<SampleModel> {
        arrList = ArrayList()
        for (i in 1..30) {
//            val sModel = SampleModel(name = "User Name " + i, age = 27).apply {
//                id = (i + 1).toLong()
//            }
            arrList.add(SampleModel(name = "User Name ", age = 27))
        }
        return arrList
    }

    private fun loadMoreData(l: (Boolean, ArrayList<SampleModel>?) -> Unit) {
        var onLoadComplete: ((Boolean, ArrayList<SampleModel>?) -> Unit)? = null
        onLoadComplete = l
        val tempList: ArrayList<SampleModel> = ArrayList()
        if (arrList.size>=60){
            onLoadComplete.invoke(false, tempList)
            return
        }
        Handler().postDelayed(Runnable {
            for (i in 1..30) {
                tempList.add(SampleModel(name = "User Name ", age = 27))
            }
            onLoadComplete.invoke(true, tempList)
        }, 2000)
    }
}