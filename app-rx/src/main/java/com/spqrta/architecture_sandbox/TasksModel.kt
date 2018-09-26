package com.spqrta.architecture_sandbox

class TasksModel {
    private object Holder { val INSTANCE = TasksModel() }

    companion object {
        val instance: TasksModel by lazy { Holder.INSTANCE }
    }

    fun getTasks(): List<Task> {
        return listOf(
                Task.default(),
                Task.default(),
                Task.default(),
                Task.default(),
                Task.default(),
                Task.default(),
                Task.default()
        )
    }

}