package com.spqrta.app_mvvm.task

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.spqrta.app_mvvm.*
import com.spqrta.app_mvvm.data.TasksInteractor
import com.spqrta.common.model.Task


class TaskViewModel(
        private val id: Int,
        private val tasksInteractor: TasksInteractor
) : BaseStateViewModel() {
    init {
        state.value = JustLoading
        loadTask()
    }

    private fun loadTask() {
        tasksInteractor.getTask(id).subscribeManaged({ task ->
            state.value = Success(task)
        }, {
            state.value = JustError(it)
        })
    }

    class Success(val task: Task) : State<Payload>()
}

class TaskViewModelFactory(private val id: Int) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TaskViewModel(id, TasksInteractor.instance) as T
    }

}
