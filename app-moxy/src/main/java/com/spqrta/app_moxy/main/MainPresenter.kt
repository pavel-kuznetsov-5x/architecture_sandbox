package com.spqrta.app_moxy.main

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.spqrta.common.LoadingState
import com.spqrta.common.TasksModel


@InjectViewState
class MainPresenter() : MvpPresenter<MainView>() {

    init {
        update()
    }

    fun update() {
        viewState.displayState(LoadingState.LOADING)
        TasksModel.INSTANCE.getTasks({
            viewState.displayState(LoadingState.DEFAULT)
            viewState.displayTasks(it)
        }, {
            viewState.displayState(LoadingState.DEFAULT)
            viewState.displayError(it)
        })
    }

}
