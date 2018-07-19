package com.example.parth.kotlinpractice_2.gallery


import com.example.parth.kotlinpractice_2.R
import com.example.parth.kotlinpractice_2.support.CoreFragment

class GalleryFragment : CoreFragment<GalleryFragment, GalleryViewModel>() {

    override fun getFragmentContext(): GalleryFragment = this

    override fun getLayoutView(): Int = R.layout.fragment_gallery

    override fun createViewModel(): GalleryViewModel = GalleryViewModel(coreFragment)

    override fun workArea() {
        vm?.changeText()
    }
}
