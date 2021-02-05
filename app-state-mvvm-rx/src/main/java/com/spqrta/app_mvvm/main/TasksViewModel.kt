package com.spqrta.app_mvvm.main

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.spqrta.app_mvvm.*
import com.spqrta.app_mvvm.data.TasksInteractor
import com.spqrta.common.tasks.Task


class TasksViewModel(private val tasksInteractor: TasksInteractor) : BaseStateViewModel() {
    init {
        state.value = JustLoading
        loadTasks()
    }

    private fun loadTasks() {
        tasksInteractor.getTasks().subscribeManaged({ tasks ->
            state.value = Success(tasks)
        }, {
            state.value = JustError(it)
        })
    }

    fun update() {
        unsubscribeAll()
        state.value = JustLoading
        loadTasks()
    }

    class Success(val tasks: List<Task>) : State()
}

class TasksViewModelFactory() : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TasksViewModel(TasksInteractor.instance) as T
    }

}
