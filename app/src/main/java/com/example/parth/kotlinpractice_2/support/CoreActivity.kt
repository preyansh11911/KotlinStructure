package com.example.parth.kotlinpractice_2.support

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v4.view.GravityCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.parth.kotlinpractice_2.R
import com.example.parth.kotlinpractice_2.databinding.ActivityCoreBinding
import kotlinx.android.synthetic.main.activity_drawer.*
import kotlinx.android.synthetic.main.app_bar_drawer.*
import kotlinx.android.synthetic.main.tool_bar.*


abstract class CoreActivity<T : CoreActivity<T, B, VM>, B : ViewDataBinding, VM : CoreViewModel> : AppCompatActivity() {

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

    override fun setContentView(childView: View?) {
        val lp = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        coreBinding.coreContent.addView(childView, lp)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    fun setNavigationDrawerContentView(childLayoutRes: Int) {
        binding = DataBindingUtil.inflate(layoutInflater, childLayoutRes, null, false)
        val lp = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        coreBinding.includedNavigationDrawer!!.includedAppBar!!.navigationDrawerContent.addView(binding.root, lp)
    }

    fun setDefaults(activity: T, layoutRes: Int) {
        this.activity = activity
        this.layoutRes = layoutRes
        if (hasNavigationDrawer()) {
            removeActionBar(R.layout.activity_drawer)
            activity.setSupportActionBar(toolbar_navigation_drawer)
            setNavigationDrawerContentView(layoutRes)
        }
        setActionBar(layoutRes)
    }

    private fun setActionBar(childLayoutRes: Int) {
        val hasActionBar = hasActionbar()
        val isCustomActionBar = isCustomActionbar()
        val isBackEnabled = isBackEnabled()
        val actionBarTitle = getActionBarTitle()

        if (hasActionBar) {
            if (isCustomActionBar) {
                removeActionBar(childLayoutRes)
                coreViewModel.isCustomActionbar.set(isCustomActionBar)
                coreViewModel.actionBarTitle.set(actionBarTitle)
                coreViewModel.isBackEnabled.set(isBackEnabled)
                activity.setSupportActionBar(tool_bar_box)
            } else {
                setBindings(childLayoutRes)
                activity.title = actionBarTitle
                activity.supportActionBar?.setDisplayHomeAsUpEnabled(isBackEnabled)
                activity.supportActionBar?.setDisplayShowHomeEnabled(isBackEnabled)
            }
        } else {
            removeActionBar(childLayoutRes)
        }
    }

    private fun removeActionBar(childLayoutRes: Int) {
        activity.setTheme(R.style.AppTheme_NoActionBar)
        setBindings(childLayoutRes)
        setVM(binding)
        setCoreVM()
    }

    private fun setBindings(childLayoutRes: Int) {
        coreBinding = DataBindingUtil.setContentView(this, R.layout.activity_core)
        binding = DataBindingUtil.inflate(layoutInflater, childLayoutRes, null, false)
        setContentView(binding.root)
    }

    private fun setCoreVM() {
        coreBinding.vm = coreViewModel
    }

    private fun createCoreViewModel(): CoreViewModel {
        return CoreViewModel()
    }

    abstract fun setVM(binding: B)

    abstract fun createViewModel(activity: T): VM

    abstract fun hasActionbar(): Boolean

    abstract fun isCustomActionbar(): Boolean

    abstract fun isBackEnabled(): Boolean

    abstract fun getActionBarTitle(): String

    abstract fun hasNavigationDrawer(): Boolean
}
