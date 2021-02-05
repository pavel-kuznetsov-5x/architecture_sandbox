package com.spqrta.common.tasks

import android.os.Handler
import android.os.Looper
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TasksAsyncModel {
    private object Holder { val INSTANCE = TasksAsyncModel() }
    companion object { val INSTANCE: TasksAsyncModel by lazy { Holder.INSTANCE } }

    private val handler = Handler(Looper.getMainLooper())
    private val cache: TasksLruCache = TasksLruCache()

    fun getTasks(callback: (List<Task>) -> Unit, errorCallback: (Throwable) -> Unit) {
        GlobalScope.launch {
            try {
                val tasks = TasksProvider.getTasks()
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
            GlobalScope.launch {
                try {
                    val task = TasksProvider.getTask(id)
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