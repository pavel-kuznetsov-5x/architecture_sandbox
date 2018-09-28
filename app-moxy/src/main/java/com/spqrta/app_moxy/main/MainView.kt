package com.spqrta.app_moxy.main

import com.arellomobile.mvp.MvpView
import com.spqrta.common.Task

interface  MainView : MvpView {
    fun displayTasks(tasks: List<Task>)
}