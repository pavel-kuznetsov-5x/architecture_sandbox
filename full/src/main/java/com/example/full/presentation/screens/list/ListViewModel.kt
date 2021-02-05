package com.example.full.presentation.screens.list

import com.example.full.domain.TasksInteractor
import com.spqrta.common.base.display.BaseStateViewModel
import com.spqrta.common.base.display.JustError
import com.spqrta.common.base.display.State
import com.spqrta.common.tasks.Task

class ListViewModel(val interactor: TasksInteractor) : BaseStateViewModel() {

    fun init() {
        setInitialState()
        loadTasks()
    }

    private fun loadTasks() {
        interactor.getTasks().subscribeManaged({
            state.value = Success(it)
        }, {
            state.value = JustError(it)
        })
    }

    class Success(val tasks: List<Task>) : State()
}