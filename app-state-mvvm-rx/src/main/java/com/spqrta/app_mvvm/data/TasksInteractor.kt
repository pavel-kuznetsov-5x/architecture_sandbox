package com.spqrta.app_mvvm.data

import com.spqrta.common.model.Task
import io.reactivex.Single

class TasksInteractor(val dataSource: TasksDataSource) {

    fun getTasks(): Single<List<Task>> = dataSource.getTasks()
            .doOnSuccess {
                //here we can perform some operations e.g. caching
            }
            .map {
                //or filtering
                it
            }

    fun getTask(id: Int): Single<Task> = dataSource.getTask(id)

    companion object {
        val instance by lazy {
            TasksInteractor(TaskCustomDataSource.instance)
        }
    }

}