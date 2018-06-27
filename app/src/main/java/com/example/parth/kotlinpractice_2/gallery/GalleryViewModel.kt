package com.example.parth.kotlinpractice_2.gallery

import com.example.parth.kotlinpractice_2.support.FragmentViewModel

class GalleryViewModel(val fragment: GalleryFragment) : FragmentViewModel() {
    fun changeText() {
        fragment.rootView.txt_glry_text.text = "This string is changed to GALLERY TEXT"
    }
}