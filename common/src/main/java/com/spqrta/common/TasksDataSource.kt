package com.spqrta.common

import com.cucumber007.reusables.utils.logging.LogUtil
import com.spqrta.common.model.Task
import java.util.*

class TasksDataSource {
    val taskList = createTasksList()

    init {
        LogUtil.setDebugMode(true)
    }

    private object Holder { val INSTANCE = TasksDataSource() }

    companion object {
        val INSTANCE: TasksDataSource by lazy { Holder.INSTANCE }
        const val DELAY = 3000
        const val ERROR_PROBABILITY = 0.2
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
        LogUtil.logDebug("Loading tasks...", "");
        if(Random().nextDouble() < ERROR_PROBABILITY) throw NullPointerException("Custom immediate error")
        Thread.sleep(DELAY.toLong())
        if(Random().nextDouble() < ERROR_PROBABILITY) throw NullPointerException("Custom result error")
        LogUtil.logDebug("Tasks loaded", "");
        return taskList
    }

    fun getTask(id: Int): Task {
        LogUtil.logDebug("Loading task", id);
        if(Random().nextDouble() < ERROR_PROBABILITY) throw NullPointerException("Custom immediate error")
        Thread.sleep(DELAY.toLong())
        if(Random().nextDouble() < ERROR_PROBABILITY) throw NullPointerException("Custom result error")
        LogUtil.logDebug("Task loaded", id)
        return taskList.first { it.id == id }
    }





}