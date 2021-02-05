package com.example.full.data.repository

import com.example.full.data.data_source.TasksRemoteDataSource
import com.spqrta.common.tasks.Task
import com.spqrta.common.tasks.TasksAsyncModel
import io.reactivex.Single
import io.reactivex.subjects.SingleSubject

object TasksRepository {

    ///////////////////////////////////////////////////////////////////////////
    // API
    ///////////////////////////////////////////////////////////////////////////

    fun getTasks(): Single<List<Task>> {
        return TasksRemoteDataSource.getTasks()
    }

    fun getTask(id: Int): Single<Task> {
        return TasksRemoteDataSource.getTask(id)
    }

    ///////////////////////////////////////////////////////////////////////////
    // Utility
    ///////////////////////////////////////////////////////////////////////////

}