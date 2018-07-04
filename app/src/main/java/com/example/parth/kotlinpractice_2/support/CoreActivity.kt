package com.example.parth.kotlinpractice_2.support

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.parth.kotlinpractice_2.R
import com.example.parth.kotlinpractice_2.databinding.ActivityCoreBinding
import com.example.parth.kotlinpractice_2.databinding.ActivityDrawerBinding
import com.example.parth.kotlinpractice_2.kotlin.showAlert
import kotlinx.android.synthetic.main.activity_drawer.*
import kotlinx.android.synthetic.main.app_bar_drawer.*
import kotlinx.android.synthetic.main.tool_bar.*


abstract class CoreActivity<T : CoreActivity<T, DB, VM>, DB : ViewDataBinding, VM : ActivityViewModel> : AppCompatActivity() {

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
    var coreVM: ActivityViewModel? = null
    val coreViewModel: ActivityViewModel
        get() {
            if (coreVM == null) coreVM = createCoreViewModel()
            return coreVM!!
        }
    var hasNavigationDrawer: Boolean = false

    override fun setContentView(childView: View?) {
        val lp = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        coreBinding.coreContent.addView(childView, lp)
    }

    override fun onBackPressed() {
        drawer_layout?.let {
            if (it.isDrawerOpen(GravityCompat.START)) {
                it.closeDrawer(GravityCompat.START)
                return
            }
        }

        if (!isBackEnabled()) {
            showAlert {
                icon(R.drawable.ic_app_exit)
                title(R.string.alert_title)
                message(R.string.alert_message)
                positiveButtonClick(R.string.YES) { super.onBackPressed() }
                negativeButtonClick(R.string.NO) { }
                makeCancelable()
                show()
            }
        } else {
            super.onBackPressed()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> this.onBackPressed()
        }
        return true
    }

    fun setNavigationDrawerContentView(childLayoutRes: Int) {
        binding = DataBindingUtil.inflate(layoutInflater, childLayoutRes, null, false)
        val lp = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0)
        lp.weight = 1F
        navigationDrawerBinding.includedAppBar!!.navigationDrawerContent!!.container.addView(binding.root, 0, lp)
    }

    fun setDefaults(activity: T, layoutRes: Int) {
        this.activity = activity
        this.layoutRes = layoutRes
        navigationDrawer()
//        if (hasNavigationDrawer) {
//            setNavigationDrawer()
//            if (hasBottomNavigation()) {
//                coreViewModel.hasBottom_DrawerNav.set(true)
//                setBottomNavDrawerMenu(bottom_navigation_nav_drawer)
//            }
//        }
        if (!hasNavigationDrawer) {
            setActionBar()
//            if (hasBottomNavigation()) {
//                coreViewModel.hasBottomNavigation.set(true)
//                setBottomNavDrawerMenu(bottom_navigation)
//            }
        }
        bottomNavigation()
        workArea(viewModel)
    }

    fun setActionBarTitle(title: String) {
        if (isCustomActionbar())
            coreViewModel.actionBarTitle.set(title)
        else
            activity.title = title
    }

    fun setNavigationDrawer() {
        removeActionBar()
        coreBinding = DataBindingUtil.setContentView(this, R.layout.activity_core)
        navigationDrawerBinding = DataBindingUtil.inflate(layoutInflater, R.layout.activity_drawer, null, false)
        setContentView(navigationDrawerBinding.root)
        setCoreVM()
        setNavDrawerVM()
        coreViewModel.hasNavigationDrawer.set(true)
        activity.setSupportActionBar(toolbar_navigation_drawer)
        activity.title = getActionBarTitle()
        setNavigationDrawerContentView(layoutRes)
        setVM(binding)
        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar_navigation_drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
    }

    private fun setNavDrawerVM() {
        navigationDrawerBinding.vm = coreViewModel
    }

    fun closeDrawer() {
        drawer_layout.closeDrawer(GravityCompat.START)
    }

    private fun setActionBar() {
        if (hasActionbar()) {
            if (isCustomActionbar()) {
                removeActionBar()
                setBindings(layoutRes)
                setCustomActionBarProperties(isCustomActionbar(), getActionBarTitle(), isBackEnabled())
                activity.setSupportActionBar(tool_bar_box)
            } else {
                setBindings(layoutRes)
                setDefaultActionBarProperties(getActionBarTitle(), isBackEnabled())
            }
        } else {
            removeActionBar()
            setBindings(layoutRes)
        }
    }

    private fun setDefaultActionBarProperties(actionBarTitle: String, isBackEnabled: Boolean) {
        setActionBarTitle(actionBarTitle)
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(isBackEnabled)
        activity.supportActionBar?.setDisplayShowHomeEnabled(isBackEnabled)
    }

    private fun setCustomActionBarProperties(isCustomActionBar: Boolean, actionBarTitle: String, isBackEnabled: Boolean) {
        coreViewModel.isCustomActionbar.set(isCustomActionBar)
        setActionBarTitle(actionBarTitle)
        coreViewModel.isBackEnabled.set(isBackEnabled)
    }

    private fun removeActionBar() {
        activity.setTheme(R.style.AppTheme_NoActionBar)
    }

    private fun setBindings(childLayoutRes: Int) {
        coreBinding = DataBindingUtil.setContentView(this, R.layout.activity_core)
        binding = DataBindingUtil.inflate(layoutInflater, childLayoutRes, null, false)
        setContentView(binding.root)
        setCoreVM()
        setVM(binding)
    }

    private fun setCoreVM() {
        coreBinding.vm = coreViewModel
    }

    private fun createCoreViewModel(): ActivityViewModel {
        return ActivityViewModel(activity)
    }

    abstract fun setVM(binding: DB)

    abstract fun createViewModel(activity: T): VM

    abstract fun getActionBarTitle(): String

    abstract fun workArea(viewModel: VM)

    open fun hasActionbar(): Boolean {
        return true
    }

    open fun isCustomActionbar(): Boolean {
        return false
    }

    open fun isBackEnabled(): Boolean {
        return false
    }

    /**
     * Override this method to set BottomNavigation in activity
     */
    open fun bottomNavigation() {}


    /**
     * Override this method to set NavigationDrawer in activity
     */
    open fun navigationDrawer() {}
}