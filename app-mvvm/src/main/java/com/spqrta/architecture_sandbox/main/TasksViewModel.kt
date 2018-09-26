package com.spqrta.architecture_sandbox.main

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.spqrta.architecture_sandbox.Task
import com.spqrta.architecture_sandbox.model.TasksModel


class TasksViewModel : ViewModel() {

    val tasksLiveData: MutableLiveData<List<Task>> = MutableLiveData()

    init {
         TasksModel.instance.getTasks { tasks ->
            tasksLiveData.value = tasks
        }
    }
}
