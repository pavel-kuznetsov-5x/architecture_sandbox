package com.spqrta.app_mvvm.data

import com.spqrta.common.model.Task
import io.reactivex.Single

class TasksInteractor(val tasksRepository: TasksRepository) {

    //example of business logic
    fun getFilteredTasks(): Single<List<Task>> = tasksRepository.getTasks()
            .map { it.filter { true } }

    fun getTasks(): Single<List<Task>> = tasksRepository.getTasks()

    fun getTask(id: Int): Single<Task> = tasksRepository.getTask(id)

    companion object {
        val instance by lazy {
            TasksInteractor(TasksRepository.instance)
        }
    }

}