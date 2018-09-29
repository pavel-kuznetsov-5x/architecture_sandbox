package com.spqrta.app_mvvm.main

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.spqrta.common.LoadingState
import com.spqrta.common.SingleLiveEvent
import com.spqrta.common.Task
import com.spqrta.common.TasksModel


class TasksViewModel : ViewModel() {

    val tasksLiveData: MutableLiveData<List<Task>> = MutableLiveData()
    val stateLiveData: MutableLiveData<LoadingState> = MutableLiveData()
    val errorLiveEvent: SingleLiveEvent<Throwable> = SingleLiveEvent()

    init {
        update()
    }

    fun update() {
        stateLiveData.value = LoadingState.LOADING
        TasksModel.INSTANCE.getTasks ({ tasks ->
            stateLiveData.value = LoadingState.DEFAULT
            tasksLiveData.value = tasks
        }, {
            stateLiveData.value = LoadingState.DEFAULT
            errorLiveEvent.value = it
        })
    }
}
