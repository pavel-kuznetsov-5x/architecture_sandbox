package com.spqrta.common

import android.os.Handler
import android.os.Looper
import kotlinx.coroutines.experimental.launch

class TasksModel {
    private object Holder { val INSTANCE = TasksModel() }

    companion object {
        val INSTANCE: TasksModel by lazy { Holder.INSTANCE }
    }

    private val handler = Handler(Looper.getMainLooper())

    fun getTasks(callback: (List<Task>) -> Unit, errorCallback: (Throwable) -> Unit) {
        launch {
            try {
                val tasks = TasksDataSource.INSTANCE.getTasks()
                handler.post {
                    callback(tasks)
                }
            } catch (e: Throwable) {
                handler.post {
                    errorCallback(e)
                }
            }


        }
    }
}