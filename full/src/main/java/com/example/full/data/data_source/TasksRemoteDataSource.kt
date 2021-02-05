package com.example.full.data.data_source

import com.spqrta.common.tasks.Task
import com.spqrta.common.tasks.TasksMockRetrofitApi
import com.spqrta.common.tasks.TasksProvider
import com.spqrta.reusables.utility.utils.applySchedulers
import io.reactivex.Single

object TasksRemoteDataSource {

    fun getTasks(): Single<List<Task>> {
        return TasksMockRetrofitApi.getTasks()
                .applySchedulers()
    }

    fun getTask(id: Int): Single<Task> {
        return TasksMockRetrofitApi.getTask(id)
                .applySchedulers()
    }

}