package com.spqrta.common.model

import android.os.Handler
import android.os.Looper
import com.spqrta.common.TasksDataSource
import kotlinx.coroutines.experimental.launch

class TasksModel {
    private object Holder { val INSTANCE = TasksModel() }
    companion object { val INSTANCE: TasksModel by lazy { Holder.INSTANCE } }

    private val handler = Handler(Looper.getMainLooper())
    private val cache: TasksLruCache = TasksLruCache()

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

    fun getTask(id: Int, callback: (Task) -> Unit, errorCallback: (Throwable) -> Unit) {
        val cachedTask = cache.get(id)
        if(cachedTask != null) callback(cachedTask)
        else {
            launch {
                try {
                    val task = TasksDataSource.INSTANCE.getTask(id)
                    handler.post {
                        cache.put(id, task)
                        callback(task)
                    }
                } catch (e: Throwable) {
                    handler.post {
                        errorCallback(e)
                    }
                }

            }
        }
    }

    fun cached(id: Int, getter: () -> Task) {



    }
}