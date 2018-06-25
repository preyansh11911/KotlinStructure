package com.example.parth.kotlinpractice_2.gallery


import com.example.parth.kotlinpractice_2.R
import com.example.parth.kotlinpractice_2.databinding.FragmentGalleryBinding
import com.example.parth.kotlinpractice_2.support.CoreFragment

class GalleryFragment : CoreFragment<GalleryFragment, FragmentGalleryBinding>() {

    override fun getFragmentContext(): GalleryFragment = this

    override fun getLayoutView(): Int = R.layout.fragment_gallery
}
