package com.example.parth.kotlinpractice_2.support

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.design.widget.NavigationView
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.parth.kotlinpractice_2.R
import com.example.parth.kotlinpractice_2.databinding.ActivityCoreBinding
import com.example.parth.kotlinpractice_2.databinding.ActivityDrawerBinding
import kotlinx.android.synthetic.main.activity_drawer.*
import kotlinx.android.synthetic.main.app_bar_drawer.*
import kotlinx.android.synthetic.main.tool_bar.*


abstract class CoreActivity<T : CoreActivity<T, DB, VM>, DB : ViewDataBinding, VM : CoreViewModel> : AppCompatActivity() {

    lateinit var activity: T
    var layoutRes: Int = 0
    lateinit var coreBinding: ActivityCoreBinding
    lateinit var navigationDrawerBinding: ActivityDrawerBinding
    lateinit var binding: DB
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
        coreViewModel.onBackPressed()
    }

    fun setNavigationDrawerContentView(childLayoutRes: Int) {
        binding = DataBindingUtil.inflate(layoutInflater, childLayoutRes, null, false)
        val lp = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0)
        lp.weight = 1F
        coreBinding.includedNavigationDrawer!!.includedAppBar!!.navigationDrawerContent!!.container.addView(binding.root, 0, lp)
    }

    fun setDefaults(activity: T, layoutRes: Int) {
        this.activity = activity
        this.layoutRes = layoutRes
        if (hasNavigationDrawer()) {
            setUpNavigationDrawer(activity, layoutRes)
            if (hasBottomNavigation())
                coreViewModel.hasBottom_DrawerNav.set(true)
        } else {
            setActionBar(layoutRes)
            if (hasBottomNavigation())
                coreViewModel.hasBottomNavigation.set(true)
        }
    }

    private fun setUpNavigationDrawer(activity: T, layoutRes: Int) {
        removeActionBar()
        coreBinding = DataBindingUtil.setContentView(this, R.layout.activity_core)
        setCoreVM()
        coreViewModel.hasNavigationDrawer.set(true)
        activity.setSupportActionBar(toolbar_navigation_drawer)
        activity.title = getActionBarTitle()
        setNavigationDrawerContentView(layoutRes)
        setVM(binding)
        setNavigationDrawerMenu(nav_view, coreViewModel)
        setNavigationDrawerHeader(nav_view, coreViewModel)
        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar_navigation_drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
    }

    private fun setActionBar(childLayoutRes: Int) {
        val hasActionBar = hasActionbar()
        val isCustomActionBar = isCustomActionbar()
        val isBackEnabled = isBackEnabled()
        val actionBarTitle = getActionBarTitle()

        if (hasActionBar) {
            if (isCustomActionBar) {
                removeActionBar()
                setBindings(childLayoutRes)
                setCustomActionBarProperties(isCustomActionBar, actionBarTitle, isBackEnabled)
                activity.setSupportActionBar(tool_bar_box)
            } else {
                setBindings(childLayoutRes)
                setDefaultActionBarProperties(actionBarTitle, isBackEnabled)
            }
        } else {
            removeActionBar()
            setBindings(childLayoutRes)
        }
    }

    private fun setDefaultActionBarProperties(actionBarTitle: String, isBackEnabled: Boolean) {
        activity.title = actionBarTitle
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(isBackEnabled)
        activity.supportActionBar?.setDisplayShowHomeEnabled(isBackEnabled)
    }

    private fun setCustomActionBarProperties(isCustomActionBar: Boolean, actionBarTitle: String, isBackEnabled: Boolean) {
        coreViewModel.isCustomActionbar.set(isCustomActionBar)
        coreViewModel.actionBarTitle.set(actionBarTitle)
        coreViewModel.isBackEnabled.set(isBackEnabled)
    }

    private fun removeActionBar() {
        activity.setTheme(R.style.AppTheme_NoActionBar)
    }

    private fun setBindings(childLayoutRes: Int) {
        coreBinding = DataBindingUtil.setContentView(this, R.layout.activity_core)
        binding = DataBindingUtil.inflate(layoutInflater, childLayoutRes, null, false)
        setContentView(binding.root)
        setVM(binding)
        setCoreVM()
    }

    private fun setCoreVM() {
        coreBinding.vm = coreViewModel
    }

    private fun createCoreViewModel(): CoreViewModel {
        return CoreViewModel(activity)
    }

    abstract fun setVM(binding: DB)

    abstract fun createViewModel(activity: T): VM

    abstract fun hasActionbar(): Boolean

    abstract fun isCustomActionbar(): Boolean

    abstract fun isBackEnabled(): Boolean

    abstract fun getActionBarTitle(): String

    abstract fun hasNavigationDrawer(): Boolean

    abstract fun setNavigationDrawerMenu(navigationView: NavigationView, coreViewModel: CoreViewModel)

    abstract fun setNavigationDrawerHeader(navigationView: NavigationView, coreViewModel: CoreViewModel)

    abstract fun hasBottomNavigation(): Boolean
}
