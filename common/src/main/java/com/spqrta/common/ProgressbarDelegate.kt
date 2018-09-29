package com.spqrta.common

import android.support.v4.widget.SwipeRefreshLayout

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