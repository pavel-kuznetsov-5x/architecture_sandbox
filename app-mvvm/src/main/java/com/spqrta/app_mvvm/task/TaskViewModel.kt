package com.spqrta.app_mvvm.task

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.spqrta.common.LoadingState
import com.spqrta.common.SingleLiveEvent
import com.spqrta.common.model.Task
import com.spqrta.common.model.TasksModel


class TaskViewModel(val id: Int) : ViewModel() {

    val taskLiveData: MutableLiveData<Task> = MutableLiveData()
    val stateLiveData: MutableLiveData<LoadingState> = MutableLiveData()
    val errorLiveEvent: SingleLiveEvent<Throwable> = SingleLiveEvent()

    init {
        stateLiveData.value = LoadingState.LOADING
        TasksModel.INSTANCE.getTask (id, { task ->
            stateLiveData.value = LoadingState.DEFAULT
            taskLiveData.value = task
        }, {
            stateLiveData.value = LoadingState.DEFAULT
            errorLiveEvent.value = it
        })
    }

}

class TaskViewModelFactory(private val id: Int) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TaskViewModel(id) as T
    }

}
