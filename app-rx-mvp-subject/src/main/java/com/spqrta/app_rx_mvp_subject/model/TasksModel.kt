package com.spqrta.app_rx_mvp_subject.model

import com.spqrta.common.Task
import com.spqrta.common.TasksDataSource
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class TasksModel {
    private object Holder { val INSTANCE = TasksModel() }

    companion object {
        val INSTANCE: TasksModel by lazy { Holder.INSTANCE }
    }

    fun getTasksSingle(): Single<List<Task>> {
        return Single.fromCallable { TasksDataSource.INSTANCE.getTasks() }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}