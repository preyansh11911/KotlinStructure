package com.example.parth.kotlinpractice_2.slideshow


import android.support.v4.app.Fragment
import com.example.parth.kotlinpractice_2.R
import com.example.parth.kotlinpractice_2.databinding.FragmentSlideshowBinding
import com.example.parth.kotlinpractice_2.support.CoreFragment

/**
 * A simple [Fragment] subclass.
 *
 */
class SlideshowFragment : CoreFragment<SlideshowFragment, FragmentSlideshowBinding>() {

    override fun getFragmentContext(): SlideshowFragment = this

    override fun getLayoutView(): Int = R.layout.fragment_slideshow
}
