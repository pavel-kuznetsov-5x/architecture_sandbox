package com.example.full.presentation.screens.task

import com.example.full.domain.TasksInteractor
import com.spqrta.common.base.display.BaseStateViewModel
import com.spqrta.common.base.display.JustError
import com.spqrta.common.base.display.State
import com.spqrta.common.tasks.Task

class TaskViewModel(val interactor: TasksInteractor) : BaseStateViewModel() {

    fun init(taskId: Int) {
        setInitialState()
        loadTask(taskId)
    }

    private fun loadTask(taskId: Int) {
        interactor.getTask(taskId).subscribeManaged({
            state.value = Success(it)
        }, {
            state.value = JustError(it)
        })
    }

    class Success(val task: Task) : State()
}