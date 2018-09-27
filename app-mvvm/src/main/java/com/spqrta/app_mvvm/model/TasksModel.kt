package com.spqrta.app_mvvm.model

import com.spqrta.common.Task
import com.spqrta.common.TasksDataSource

class TasksModel {
    private object Holder { val INSTANCE = TasksModel() }

    companion object {
        val INSTANCE: TasksModel by lazy { Holder.INSTANCE }
    }

    fun getTasks(callback: (List<Task>) -> Unit) {
        TasksDataSource.INSTANCE.getTasks(callback)
    }
}