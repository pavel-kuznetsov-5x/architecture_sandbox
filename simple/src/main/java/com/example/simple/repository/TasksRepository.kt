package com.example.simple.repository

import com.spqrta.common.tasks.Task
import com.spqrta.common.tasks.TasksAsyncModel
import io.reactivex.Single
import io.reactivex.subjects.SingleSubject

object TasksRepository {

    ///////////////////////////////////////////////////////////////////////////
    // API
    ///////////////////////////////////////////////////////////////////////////

    fun getTasks(): Single<List<Task>> {
        val source: SingleSubject<List<Task>> = SingleSubject.create()
        TasksAsyncModel.INSTANCE.getTasks({
            source.onSuccess(it)
        }, {
            source.onError(it)
        })
        return source
    }

    fun getTask(id: Int): Single<Task> {
        val source: SingleSubject<Task> = SingleSubject.create()
        TasksAsyncModel.INSTANCE.getTask(id, {
            source.onSuccess(it)
        }, {
            source.onError(it)
        })
        return source
    }

    ///////////////////////////////////////////////////////////////////////////
    // Utility
    ///////////////////////////////////////////////////////////////////////////

}