package com.spqrta.app_mvvm.main

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.spqrta.app_mvvm.*
import com.spqrta.app_mvvm.data.TasksInteractor
import com.spqrta.common.model.Task


class TasksViewModel(private val tasksInteractor: TasksInteractor) : BaseStateViewModel() {
    init {
        state.value = JustLoading
        loadTasks()
    }

    private fun loadTasks() {
        tasksInteractor.getTasks().subscribeManaged({ tasks ->
            state.value = Initial(tasks)
        }, {
            state.value = JustError(it)
        })
    }

    fun update() {
        unsubscribeAll()
        state.value = JustLoading
        loadTasks()
    }

    class Initial(val tasks: List<Task>) : State<Payload>()
}

class TasksViewModelFactory() : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TasksViewModel(TasksInteractor.instance) as T
    }

}
