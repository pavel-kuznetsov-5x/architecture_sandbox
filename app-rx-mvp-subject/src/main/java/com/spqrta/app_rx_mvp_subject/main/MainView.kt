package com.spqrta.app_rx_mvp_subject.main

import com.spqrta.common.LoadingState
import com.spqrta.common.Task

interface MainView {
    fun displayTasks(tasks: List<Task>)
    fun displayState(state: LoadingState)
}