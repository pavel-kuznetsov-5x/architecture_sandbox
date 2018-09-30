package com.spqrta.app_moxy.main

import com.arellomobile.mvp.MvpView
import com.spqrta.common.LoadingState
import com.spqrta.common.model.Task

interface  MainView : MvpView {
    fun displayTasks(tasks: List<Task>)
    fun displayState(loadingState: LoadingState)
    fun displayError(throwable: Throwable)
}