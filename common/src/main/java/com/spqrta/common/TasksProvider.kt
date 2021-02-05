package com.spqrta.common

import com.spqrta.common.model.Task
import java.util.*

class TasksProvider {
    val taskList = createTasksList()

    init {
//        LogUtil.setDebugMode(true)
    }

    private object Holder { val INSTANCE = TasksProvider() }

    companion object {
        val INSTANCE: TasksProvider by lazy { Holder.INSTANCE }
        const val DELAY = 500
        const val ERROR_PROBABILITY = 0.0
    }

    private fun createTasksList() = listOf(
            Task.default(),
            Task.default(),
            Task.default(),
            Task.default(),
            Task.default(),
            Task.default(),
            Task.default()
    )

    fun getTasks(): List<Task> {
//        LogUtil.logDebug("Loading tasks...", "");
        if(Random().nextDouble() < ERROR_PROBABILITY) throw NullPointerException("Simulated immediate error")
        Thread.sleep(DELAY.toLong())
        if(Random().nextDouble() < ERROR_PROBABILITY) throw NullPointerException("Simulated result error")
//        LogUtil.logDebug("Tasks loaded", "");
        return taskList
    }

    fun getTask(id: Int): Task {
//        LogUtil.logDebug("Loading task", id);
        if(Random().nextDouble() < ERROR_PROBABILITY) throw NullPointerException("Simulated immediate error")
        Thread.sleep(DELAY.toLong())
        if(Random().nextDouble() < ERROR_PROBABILITY) throw NullPointerException("Simulated result error")
//        LogUtil.logDebug("Task loaded", id)
        return taskList.first { it.id == id }
    }





}