package com.example.parth.kotlinpractice_2.tools


import android.support.v4.app.Fragment
import com.example.parth.kotlinpractice_2.R
import com.example.parth.kotlinpractice_2.databinding.FragmentToolsBinding
import com.example.parth.kotlinpractice_2.support.CoreFragment

/**
 * A simple [Fragment] subclass.
 *
 */
class ToolsFragment : CoreFragment<ToolsFragment, FragmentToolsBinding>() {

    override fun getFragmentContext(): ToolsFragment = this

    override fun getLayoutView(): Int = R.layout.fragment_tools
}
