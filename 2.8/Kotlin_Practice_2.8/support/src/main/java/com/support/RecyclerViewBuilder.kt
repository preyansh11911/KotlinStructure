package com.support

import android.support.annotation.LayoutRes
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

fun <T : POJOModel> RecyclerView.setUp(@LayoutRes layoutResID: Int, itemList: ArrayList<T>, builder: RecyclerViewBuilder<T>.() -> Unit) = RecyclerViewBuilder<T>(this, layoutResID, itemList).apply(builder)

class RecyclerViewBuilder<T : POJOModel>
(val recyclerView: RecyclerView, val layoutResID: Int, val mItems: ArrayList<T>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val VIEW_TYPE_ITEM = 1012
    private val VIEW_TYPE_LOADER = 1022

    var itemView: Int = 0
        get() = field
        set(value) {
            field = value
        }

    var contentBindingListener: ((T, View) -> Unit)? = null
    private lateinit var loadMoreListener: () -> Unit
    private var isLoading: Boolean = false
    var hasMore = false

    init {
        setHasStableIds(true)
        recyclerView.adapter = this
    }

    fun setLayoutManager(layoutManager: LinearLayoutManager) {
        recyclerView.layoutManager = layoutManager
    }

    fun setNestedScrollingEnabled(isEnabled: Boolean) {
        recyclerView.isNestedScrollingEnabled = isEnabled
    }

    fun contentBinder(l: ((T, View) -> Unit)?) {
        contentBindingListener = l
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == VIEW_TYPE_ITEM) {
            val v = LayoutInflater.from(parent.context).inflate(layoutResID, parent, false)
            return CustomViewHolder(v)
        } else {
            val v = LayoutInflater.from(parent.context).inflate(R.layout.load_more_progressbar, parent, false)
            return LoaderViewHolder(v)
        }
    }

    override fun getItemCount(): Int = mItems.size

    override fun getItemId(position: Int): Long = mItems[position].id

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is RecyclerViewBuilder<*>.CustomViewHolder) {
            contentBindingListener?.invoke(mItems[position], holder.itemView)
        }
    }

    fun removeItem(item: T) {
        mItems.remove(item)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return if (mItems[position].isLoader) VIEW_TYPE_LOADER else VIEW_TYPE_ITEM
    }

    inner class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    inner class LoaderViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView)

    fun enableLoadMore(l: () -> Unit) {
        loadMoreListener = l
        hasMore = true

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                val totalItemCount = recyclerView?.layoutManager?.getItemCount()
                val lastVisibleItem = (recyclerView?.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                val visibleThreshold = 5
                if ((hasMore && !isLoading) && totalItemCount!! <= (lastVisibleItem + visibleThreshold)) {
                    loadMoreListener.invoke()
                    isLoading = true
                }
            }
        })
    }

    fun showLoader() {
        val pModel = POJOModel().apply { isLoader = true } as T
        mItems.add(pModel)
        notifyItemInserted(itemCount - 1)
    }

    fun stopLoader() {
        isLoading = false
    }

    fun removeLastItem() {
        mItems.removeAt(itemCount - 1)
        notifyItemRemoved(itemCount)
    }

    fun addAll(list: ArrayList<T>) {
        val position = itemCount
        mItems.addAll(position, list)
        notifyItemRangeInserted(position, list.size)
    }
}