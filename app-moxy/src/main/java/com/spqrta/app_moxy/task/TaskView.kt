package com.spqrta.app_moxy.task

import com.arellomobile.mvp.MvpView
import com.spqrta.common.LoadingState
import com.spqrta.common.model.Task

interface TaskView : MvpView {
    fun displayTask(task: Task)
    fun displayState(loadingState: LoadingState)
    fun displayError(throwable: Throwable)
}