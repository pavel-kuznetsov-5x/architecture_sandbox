package com.spqrta.common.tasks

import io.reactivex.Single

object TasksMockRetrofitApi {

    fun getTasks(): Single<List<Task>> {
        return Single.fromCallable {
            TasksProvider.getTasks()
        }
    }

    fun getTask(id: Int): Single<Task> {
        return Single.fromCallable {
            TasksProvider.getTask(id)
        }
    }

}