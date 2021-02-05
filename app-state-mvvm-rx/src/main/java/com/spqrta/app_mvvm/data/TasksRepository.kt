package com.spqrta.app_mvvm.data

import com.spqrta.common.tasks.Task
import io.reactivex.Single

class TasksRepository(private val dataSource: TasksDataSource) {
    fun getTasks(): Single<List<Task>> = dataSource.getTasks()
            .doOnSuccess {
                //here we can perform some operations e.g. caching
            }
            .map {
                //or data mapping
                it
            }

    fun getTask(id: Int): Single<Task> = dataSource.getTask(id)

    companion object {
        val instance by lazy {
            TasksRepository(TaskCustomDataSource.instance)
        }
    }
}