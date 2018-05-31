package com.example.parth.kotlinpractice_2.support

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.parth.kotlinpractice_2.R
import com.example.parth.kotlinpractice_2.databinding.ActivityCoreBinding
import android.view.ViewGroup
import android.widget.LinearLayout


abstract class CoreActivity<T : CoreActivity<T, B, VM>, B : ViewDataBinding, VM : ActivityViewModel> : AppCompatActivity() {

    lateinit var activity: T
    var layoutRes: Int = 0
    lateinit var coreBinding: ActivityCoreBinding
    lateinit var binding: B
    var vm: VM? = null
    val viewModel: VM
        get() {
            if (vm == null) vm = createViewModel(activity)
            return vm!!
        }
    var coreVM: CoreViewModel? = null
    val coreViewModel: CoreViewModel
        get() {
            if (coreVM == null) coreVM = createCoreViewModel()
            return coreVM!!
        }

    override fun setContentView(view: View?) {
        val lp = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        coreBinding.coreContent.addView(view, lp)
    }

    fun setDefaults(activity: T, layoutRes: Int) {
        this.activity = activity
        this.layoutRes = layoutRes
        setActionBar()
        setVM(binding)
        setCoreVM()
    }

    private fun setActionBar() {
        val hasActionBar = hasActionbar()
        val isCustomActionBar = isCustomActionbar()
        val isBackEnabled = isBackEnabled()
        val actionBarTitle = getActionBarTitle()

        if (hasActionBar) {
            if (isCustomActionBar) {
                removeActionBar()
                coreViewModel.isCustomActionbar.set(isCustomActionBar)
                coreViewModel.actionBarTitle.set(actionBarTitle)
                coreViewModel.isBackEnabled.set(isBackEnabled)
            } else {
                setBindings()
                activity.title = actionBarTitle
                activity.supportActionBar?.setDisplayHomeAsUpEnabled(isBackEnabled)
                activity.supportActionBar?.setDisplayShowHomeEnabled(isBackEnabled)
            }
        } else {
            removeActionBar()
        }

    }

    private fun removeActionBar() {
        activity.setTheme(R.style.AppTheme_NoActionBar)
        setBindings()
    }

    private fun setBindings() {
        coreBinding = DataBindingUtil.setContentView(this, R.layout.activity_core)
        binding = DataBindingUtil.inflate(layoutInflater, layoutRes, null, false)
        setContentView(binding.root)
    }

    private fun setCoreVM() {
        coreBinding.vm = coreViewModel
    }

    private fun createCoreViewModel() : CoreViewModel {
        return CoreViewModel()
    }

    abstract fun setVM(binding: B)

    abstract fun createViewModel(activity: T): VM

    abstract fun hasActionbar(): Boolean

    abstract fun isCustomActionbar(): Boolean

    abstract fun isBackEnabled(): Boolean

    abstract fun getActionBarTitle(): String
}
