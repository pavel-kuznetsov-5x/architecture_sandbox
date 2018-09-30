package com.spqrta.app_rx_mvp_subject.task

import com.spqrta.common.LoadingState
import com.spqrta.common.model.Task

interface TaskView {
    fun displayTask(task: Task)
    fun displayState(loadingState: LoadingState)
    fun displayError(throwable: Throwable)
}