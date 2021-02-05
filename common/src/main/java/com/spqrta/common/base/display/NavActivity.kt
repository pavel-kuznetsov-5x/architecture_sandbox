package com.spqrta.common.base.display

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import org.jetbrains.annotations.TestOnly

abstract class NavActivity: BaseActivity() {

    protected val allowNotBaseFragments = false

    protected lateinit var navController: NavController

    //todo reusables
    abstract val navHostId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        navController = findNavController(navHostId)

        navController.addOnDestinationChangedListener { _, destination, bundle ->
            try {
                getCurrentBaseFragment()?.onLeave()
                onDestinationChanged(destination)
            } catch (e: IndexOutOfBoundsException) {
            }
        }

    }

    protected open fun onDestinationChanged(destination: NavDestination) {

    }

    protected fun getCurrentBaseFragment(): BaseFragment<*>? {
        val navHostFragment = supportFragmentManager.findFragmentById(navHostId)
        val res = navHostFragment!!.childFragmentManager.fragments[0]
        if (res is BaseFragment<*>) {
            return res
        } else {
            if(allowNotBaseFragments) {
                return null
            } else {
                throw IllegalStateException("allowNotBaseFragments = false")
            }
        }
    }

    protected fun getCurrentFragment(): Fragment {
        val navHostFragment = supportFragmentManager.findFragmentById(navHostId)
        return navHostFragment!!.childFragmentManager.fragments[0]
    }

    override fun onBackPressed() {
        if(getCurrentBaseFragment()?.onBackPressed() == false) {
            super.onBackPressed()
        }
    }

    @TestOnly
    fun getNavigationController(): NavController {
        return navController
    }
}