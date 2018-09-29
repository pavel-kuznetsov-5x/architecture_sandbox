package com.spqrta.common.delegates

import android.support.v4.widget.SwipeRefreshLayout
import com.spqrta.common.LoadingState

abstract class ProgressbarDelegate {
    abstract fun show()
    abstract fun hide()

    fun applyState(loadingState: LoadingState) {
        when(loadingState) {
            LoadingState.DEFAULT -> hide()
            LoadingState.LOADING -> show()
        }
    }
}

class StrProgressbarDelegate(val strLayout: SwipeRefreshLayout): ProgressbarDelegate() {

    override fun show() {
        strLayout.isRefreshing = true
    }

    override fun hide() {
        strLayout.isRefreshing = false
    }
}