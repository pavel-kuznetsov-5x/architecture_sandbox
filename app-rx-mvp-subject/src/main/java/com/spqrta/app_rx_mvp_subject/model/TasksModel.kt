package com.spqrta.app_rx_mvp_subject.model

import com.spqrta.common.Task
import com.spqrta.common.TasksDataSource
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class TasksModel {
    private object Holder { val INSTANCE = TasksModel() }

    companion object {
        val INSTANCE: TasksModel by lazy { Holder.INSTANCE }
    }

    fun getTasks(): Observable<List<Task>> {
        return TasksDataSource.INSTANCE.getTasksObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}