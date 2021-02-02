package com.spqrta.common.delegates

import android.view.View
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.spqrta.common.LoadingState

abstract class ProgressIndicatorDelegate {
    abstract fun show()
    abstract fun hide()

    fun applyState(loadingState: LoadingState) {
        when(loadingState) {
            LoadingState.DEFAULT -> hide()
            LoadingState.LOADING -> show()
        }
    }
}

class StrProgressbarDelegate(val strLayout: SwipeRefreshLayout): ProgressIndicatorDelegate() {

    override fun show() {
        strLayout.isRefreshing = true
    }

    override fun hide() {
        strLayout.isRefreshing = false
    }
}

class HideableProgressbarDelegate(val view: View): ProgressIndicatorDelegate() {

    override fun show() {
        view.visibility = View.VISIBLE
    }

    override fun hide() {
        view.visibility = View.GONE
    }
}