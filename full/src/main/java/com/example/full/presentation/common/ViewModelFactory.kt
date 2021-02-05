package com.example.full.presentation.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.full.domain.TasksInteractor
import com.example.full.presentation.screens.list.ListViewModel
import com.example.full.presentation.screens.task.TaskViewModel

object ViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return with(modelClass) {
            when {
                isAssignableFrom(ListViewModel::class.java) -> {
                    ListViewModel(TasksInteractor)
                }
                isAssignableFrom(TaskViewModel::class.java) -> {
                    TaskViewModel(TasksInteractor)
                }
                else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T
    }

}
