package com.example.simple.repository

import com.spqrta.common.model.Task
import io.reactivex.Single

object TasksRepository {

    fun getTasks(): Single<List<Task>> {
        return Single.never()
    }

    fun getTask(id: String): Single<Task> {
        return Single.never()
    }

}