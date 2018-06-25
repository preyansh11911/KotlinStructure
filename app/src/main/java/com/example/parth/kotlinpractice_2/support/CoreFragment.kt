package com.example.parth.kotlinpractice_2.support

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class CoreFragment<T : CoreFragment<T, DB, VM>, DB : ViewDataBinding, VM : FragmentViewModel> : Fragment() {

    lateinit var coreFragment: T
    lateinit var binding: DB
    lateinit var vm: VM

    fun newInstance() = this

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return getRoot()
    }

    fun getRoot(): View {
        coreFragment = getFragmentContext()
        binding = DataBindingUtil.inflate(layoutInflater, getLayoutView(), null, false)
        setVM(binding)
        return binding.root
    }

    abstract fun getLayoutView(): Int

    abstract fun getFragmentContext(): T

    fun getViewModel(): VM {
        if (vm == null) vm = createViewModel()
        return vm
    }

    abstract fun createViewModel(): VM

    abstract fun setVM(binding: DB)
}