package com.spqrta.app_moxy.task

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.spqrta.common.LoadingState
import com.spqrta.common.model.TasksModel

@InjectViewState
class TaskPresenter(val taskId: Int) : MvpPresenter<TaskView>() {

    init {
        viewState.displayState(LoadingState.LOADING)
        TasksModel.INSTANCE.getTask(taskId, { task ->
            viewState.displayState(LoadingState.DEFAULT)
            viewState.displayTask(task)
        }, {
            viewState.displayState(LoadingState.DEFAULT)
            viewState.displayError(it)
        })
    }

}