package com.spqrta.common.utility.pure

import com.spqrta.common.utility.CustomApplication

object AppUtils {
    fun ifDebugMode(func: () -> Unit) {
        if(CustomApplication.appConfig.debugMode) {
            func.invoke()
        }
    }

    fun <T> ifReleaseModeElse(release: () -> T, debug: () -> T): T {
        if(CustomApplication.appConfig.releaseMode) {
            return release.invoke()
        } else {
            return debug.invoke()
        }
    }
}