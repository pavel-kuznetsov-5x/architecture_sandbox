package com.spqrta.common.base.mixins

import android.view.View
import com.spqrta.common.base.display.JustLoading
import com.spqrta.common.base.display.Payload
import com.spqrta.common.base.display.State
import com.spqrta.common.utility.pure.hide
import com.spqrta.common.utility.pure.show

interface ProgressbarMixin {

    fun applyProgressbarMixin(state: State, progressbarView: View) {
        if (state is JustLoading) {
            progressbarView.show()
        } else {
            progressbarView.hide()
        }
    }

}