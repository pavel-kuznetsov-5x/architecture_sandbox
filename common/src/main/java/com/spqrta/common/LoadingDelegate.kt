package com.spqrta.common

import android.support.v4.widget.SwipeRefreshLayout

interface ProgressbarDelegate {
    fun show()
    fun hide()
}

class StrProgressbarDelegate(val strLayout: SwipeRefreshLayout): ProgressbarDelegate {

    override fun show() {
        strLayout.isRefreshing = true
    }

    override fun hide() {
        strLayout.isRefreshing = false
    }
}