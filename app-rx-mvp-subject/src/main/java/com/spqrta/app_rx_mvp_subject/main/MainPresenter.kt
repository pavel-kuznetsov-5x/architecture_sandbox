package com.spqrta.app_rx_mvp_subject.main

import com.spqrta.app_rx_mvp_subject.interactors.TasksInteractor
import io.reactivex.disposables.Disposable

class MainPresenter(var view: MainView?) {

    var disposable: Disposable? = null

    init {
        disposable = TasksInteractor.INSTANCE.getTasksObservable().subscribe {
            view?.displayTasks(it)
        }
    }

    fun update() {
        TasksInteractor.INSTANCE.updateTasks()
    }

    fun destroy() {
        view = null
        disposable?.dispose()
    }

}