package com.spqrta.app_rx_mvp_subject.interactors

import com.spqrta.app_rx_mvp_subject.model.TasksModel
import com.spqrta.common.model.Task
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

class TasksInteractor {
    private object Holder { val INSTANCE = TasksInteractor() }

    companion object {
        val INSTANCE: TasksInteractor by lazy { Holder.INSTANCE }
    }

    var source: BehaviorSubject<List<Task>>? = null

    fun getTasksObservable(): Observable<List<Task>> {
        if (source == null) {
            source = createTaskSourceAndLoad()
        }
        return source!!
    }

    fun updateTasks() {
        source = createTaskSourceAndLoad()
    }

    private fun createTaskSourceAndLoad(): BehaviorSubject<List<Task>> {
        val source: BehaviorSubject<List<Task>> = BehaviorSubject.create()
        TasksModel.INSTANCE.getTasksSingle().subscribe({
            source.onNext(it)
        }, {
            source.onError(it)
        })
        return source
    }
}