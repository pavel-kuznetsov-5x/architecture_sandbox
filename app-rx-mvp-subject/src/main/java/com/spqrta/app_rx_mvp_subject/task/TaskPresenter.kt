package com.spqrta.app_rx_mvp_subject.task

import com.spqrta.app_rx_mvp_subject.interactors.TaskInteractor
import com.spqrta.common.LoadingState
import com.spqrta.common.tasks.TasksAsyncModel
import io.reactivex.disposables.Disposable

class TaskPresenter(var view: TaskView?, taskId: Int) {

    var disposable: Disposable? = null

    init {
        view?.displayState(LoadingState.LOADING)
        TasksAsyncModel.INSTANCE.getTask(taskId, { task ->
            view?.displayState(LoadingState.DEFAULT)
            view?.displayTask(task)
        }, {
            view?.displayState(LoadingState.DEFAULT)
            view?.displayError(it)
        })
    }

    private fun createTaskSubscription(id: Int): Disposable {
        return TaskInteractor.INSTANCE.getTaskObservable(id).subscribe ({ task ->
            view?.displayState(LoadingState.DEFAULT)
            view?.displayTask(task)
        }, {
            view?.displayState(LoadingState.DEFAULT)
            view?.displayError(it)
        })
    }

    fun destroy() {
        view = null
        disposable?.dispose()
    }

}