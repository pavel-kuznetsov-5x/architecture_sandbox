package com.spqrta.common

import android.os.Handler
import android.os.Looper
import com.cucumber007.reusables.utils.logging.LogUtil
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch

class TasksDataSource {
    init {
        LogUtil.setDebugMode(true)
    }

    private object Holder { val INSTANCE = TasksDataSource() }

    val handler = Handler(Looper.getMainLooper())

    companion object {
        val INSTANCE: TasksDataSource by lazy { Holder.INSTANCE }
        const val DELAY = 3000
    }

    fun getTasks(callback: (List<Task>) -> Unit) {
        LogUtil.logDebug("Loading tasks...", "");
        launch {
            delay(DELAY)
            handler.post {
                LogUtil.logDebug("Tasks loaded", "");
                callback(listOf(
                        Task.default(),
                        Task.default(),
                        Task.default(),
                        Task.default(),
                        Task.default(),
                        Task.default(),
                        Task.default()
                ))
            }

        }
    }



}