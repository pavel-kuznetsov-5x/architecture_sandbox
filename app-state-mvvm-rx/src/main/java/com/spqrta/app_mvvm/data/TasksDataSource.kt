package com.spqrta.app_mvvm.data

import com.spqrta.common.model.Task
import io.reactivex.Single

interface TasksDataSource {
    fun getTasks(): Single<List<Task>>
    fun getTask(id: Int): Single<Task>
}