package com.spqrta.app_moxy.main

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.spqrta.app_moxy.model.TasksModel


@InjectViewState
class MainPresenter() : MvpPresenter<MainView>() {

    init {
        update()
    }

    fun update() {
        TasksModel.INSTANCE.getTasks {
            viewState.displayTasks(it)
        }
    }

}
