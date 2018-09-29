package com.spqrta.app_rx_mvp_subject.main

import com.spqrta.app_rx_mvp_subject.interactors.TasksInteractor
import com.spqrta.common.LoadingState
import io.reactivex.disposables.Disposable

class MainPresenter(var view: MainView?) {

    var disposable: Disposable? = null

    init {
        view?.displayState(LoadingState.LOADING)
        disposable = createTasksSubscription()
    }

    fun update() {
        view?.displayState(LoadingState.LOADING)
        disposable?.dispose()
        TasksInteractor.INSTANCE.updateTasks()
        disposable = createTasksSubscription()
    }

    private fun createTasksSubscription(): Disposable {
        return TasksInteractor.INSTANCE.getTasksObservable().subscribe ({
            view?.displayState(LoadingState.DEFAULT)
            view?.displayTasks(it)
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