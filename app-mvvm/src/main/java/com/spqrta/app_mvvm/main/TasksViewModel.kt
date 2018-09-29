package com.spqrta.app_mvvm.main

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.spqrta.app_mvvm.model.TasksModel
import com.spqrta.common.LoadingState
import com.spqrta.common.Task


class TasksViewModel : ViewModel() {

    val tasksLiveData: MutableLiveData<List<Task>> = MutableLiveData()
    val stateLiveData: MutableLiveData<LoadingState> = MutableLiveData()

    init {
        update()
    }

    fun update() {
        stateLiveData.value = LoadingState.LOADING
        TasksModel.INSTANCE.getTasks { tasks ->
            stateLiveData.value = LoadingState.DEFAULT
            tasksLiveData.value = tasks
        }
    }
}
