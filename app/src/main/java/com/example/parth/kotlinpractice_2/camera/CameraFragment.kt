package com.example.parth.kotlinpractice_2.camera


import android.support.v4.app.Fragment
import com.example.parth.kotlinpractice_2.R
import com.example.parth.kotlinpractice_2.databinding.FragmentCameraBinding
import com.example.parth.kotlinpractice_2.support.CoreFragment

/**
 * A simple [Fragment] subclass.
 *
 */
class CameraFragment : CoreFragment<CameraFragment, FragmentCameraBinding, CameraViewModel>() {

    override fun getFragmentContext(): CameraFragment = this

    override fun getLayoutView() = R.layout.fragment_camera

    override fun setVM(binding: FragmentCameraBinding) {
        binding.vm = getViewModel()
    }

    override fun createViewModel(): CameraViewModel = CameraViewModel()

}
