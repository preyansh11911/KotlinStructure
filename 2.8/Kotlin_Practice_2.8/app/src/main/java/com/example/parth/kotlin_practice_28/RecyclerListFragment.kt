package com.example.parth.kotlin_practice_28

import com.example.parth.kotlin_practice_28.databinding.FragmentListBinding
import com.support.CoreFragment_DataBinding

class RecyclerListFragment : CoreFragment_DataBinding<RecyclerListFragment, FragmentListBinding, RecyclerListViewModel>() {

    override fun workArea() {
        vm?.createVerticalList()
        vm?.createHorizontalList()
    }

    override fun getLayoutView(): Int = R.layout.fragment_list

    override fun getFragmentContext(): RecyclerListFragment = this

    override fun createViewModel(): RecyclerListViewModel = RecyclerListViewModel(this)

    override fun setVM(binding: FragmentListBinding) {
        binding.vm = vm
    }

}
