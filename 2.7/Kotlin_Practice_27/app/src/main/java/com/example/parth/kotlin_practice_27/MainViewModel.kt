package com.example.parth.kotlin_practice_27

import android.os.Handler
import android.widget.Toast
import com.example.parth.kotlin_practice_27.databinding.RowItemBinding
import com.example.parth.kotlinpractice_2.support.ActivityViewModel
import com.support.kotlin.setUpRecyclerView_Binding
import kotlinx.android.synthetic.main.activity_main.*

class MainViewModel(val mActivity: MainActivity) : ActivityViewModel(mActivity) {

    lateinit var arrList: ArrayList<SampleModel>

    init {
        mActivity.recView.setUpRecyclerView_Binding<SampleModel, RowItemBinding>(createList(),
                builder = {
                    itemView = R.layout.row_item
                    contentBinder { item, binding ->
                        binding.setOnItemClickListener {
                            Toast.makeText(mActivity, "Name : ${item.name}", Toast.LENGTH_SHORT).show()
                            arrList.remove(item)
                            mActivity.recView.adapter.notifyDataSetChanged()
                        }
                    }
                    enableLoadMore {
                        showLoader()
                        loadMoreData {hasMore ->
                            removeLastItem()
                            stopLoader()
                            if (hasMore) addAll(arrList)
                        }
                    }
                })
        mActivity.swipeRefresh.setOnRefreshListener {
            val sModel = SampleModel(name = "User Name " + ((arrList.size) + 1), age = 27)
            sModel.id = ((arrList.size) + 2).toLong()
            arrList.add(5, sModel)
            mActivity.recView.adapter.notifyDataSetChanged()
            mActivity.swipeRefresh.isRefreshing = false
        }
    }

    private fun createList(): ArrayList<SampleModel> {
        arrList = ArrayList()
        for (i in 1..30) {
            val sModel = SampleModel(name = "User Name " + i, age = 27).apply {
                id = (i + 1).toLong()
            }
            arrList.add(sModel)
        }
        return arrList
    }

    private fun loadMoreData(l: (Boolean) -> Unit) {
        var onLoadComplete: ((Boolean) -> Unit)? = null
        onLoadComplete = l
        val startIndex = arrList.size
        val endIndex = arrList.size + 30
        Handler().postDelayed(Runnable {
            if (startIndex <= 500) {
                for (i in startIndex..endIndex) {
                    val sModel = SampleModel(name = "User Name " + i, age = 27).apply {
                        id = (i + 1).toLong()
                    }
                    arrList.add(sModel)
                }
                onLoadComplete?.invoke(true)
            } else {
                onLoadComplete?.invoke(false)
            }
        }, 2000)
    }

}