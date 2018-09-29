package com.spqrta.common

import com.cucumber007.reusables.utils.logging.LogUtil
import java.util.*

class TasksDataSource {
    init {
        LogUtil.setDebugMode(true)
    }

    private object Holder { val INSTANCE = TasksDataSource() }

    companion object {
        val INSTANCE: TasksDataSource by lazy { Holder.INSTANCE }
        const val DELAY = 3000
        const val ERROR_PROBABILITY = 0.5
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
        return createTasksList()
    }





}