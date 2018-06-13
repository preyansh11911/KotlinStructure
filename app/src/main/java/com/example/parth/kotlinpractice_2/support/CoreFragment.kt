package com.example.parth.kotlinpractice_2.support

import android.databinding.ViewDataBinding
import android.support.v4.app.Fragment

class CoreFragment<T : CoreFragment<T, DB, VM>, DB : ViewDataBinding, VM : FragmentViewModel> : Fragment()