package com.spqrta.app_mvvm.main

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.spqrta.app_mvvm.model.TasksModel
import com.spqrta.common.Task


class TasksViewModel : ViewModel() {

    val tasksLiveData: MutableLiveData<List<Task>> = MutableLiveData()

    init {
         TasksModel.INSTANCE.getTasks { tasks ->
            tasksLiveData.value = tasks
        }
    }

    fun update() {

    }
}
