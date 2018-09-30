package com.spqrta.app_rx_mvp_subject.interactors

import com.spqrta.app_rx_mvp_subject.model.TasksModel
import com.spqrta.common.model.Task
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

class TaskInteractor {
    private object Holder { val INSTANCE = TaskInteractor() }

    companion object {
        val INSTANCE: TaskInteractor by lazy { Holder.INSTANCE }
    }

    var source: BehaviorSubject<Task>? = null

    fun getTaskObservable(id: Int): Observable<Task> {
        if (source == null) {
            source = createTaskSourceAndLoad(id)
        }
        return source!!
    }

    private fun createTaskSourceAndLoad(id: Int): BehaviorSubject<Task> {
        val source: BehaviorSubject<Task> = BehaviorSubject.create()
        TasksModel.INSTANCE.getTaskSingle(id).subscribe({
            source.onNext(it)
        }, {
            source.onError(it)
        })
        return source
    }
}