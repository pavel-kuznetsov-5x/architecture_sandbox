package com.spqrta.architecture_sandbox.model

import android.os.Handler
import android.os.Looper
import com.spqrta.architecture_sandbox.Task
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch

class TasksModel: RetrofitService {
    private object Holder { val INSTANCE = TasksModel() }

    val handler = Handler(Looper.getMainLooper())

    companion object {
        val instance: TasksModel by lazy { Holder.INSTANCE }
        const val DELAY = 2000
    }

    override fun getTasks(callback: (List<Task>) -> Unit) {
        launch {
            delay(DELAY)
            handler.post {
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