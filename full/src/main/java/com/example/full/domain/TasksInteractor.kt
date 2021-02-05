package com.example.full.domain

import com.example.full.data.repository.TasksRepository
import com.spqrta.common.tasks.Task
import com.spqrta.common.utility.utils.applySchedulers
import io.reactivex.Single

object TasksInteractor {

    fun getTasks(): Single<List<Task>> {
        return TasksRepository.getTasks()
                .applySchedulers()
    }

    fun getTask(id: Int): Single<Task> {
        return TasksRepository.getTask(id)
                .applySchedulers()
    }

}