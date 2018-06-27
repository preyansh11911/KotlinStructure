package com.example.parth.kotlinpractice_2.camera

import android.databinding.ObservableField
import com.example.parth.kotlinpractice_2.support.FragmentViewModel

class CameraViewModel : FragmentViewModel() {

    var cameraText = ObservableField<String>()

    fun changeText() {
        cameraText.set("This string is changed to CAMERA TEXT")
    }
}