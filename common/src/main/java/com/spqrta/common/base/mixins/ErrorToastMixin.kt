package com.spqrta.common.base.mixins

import com.spqrta.common.base.display.JustError
import com.spqrta.common.base.display.State
import com.spqrta.common.utility.Toaster

interface ErrorToastMixin {

    fun applyErrorToastMixin(state: State) {
        if (state is JustError) {
//            CustomApplication.analytics().logException(state.exception)
            applyErrorToastMixin(state.exception)
        }
    }

    fun applyErrorToastMixin(exception: Throwable) {
        Toaster.show(exception.message ?: "Unknown error")
    }

}