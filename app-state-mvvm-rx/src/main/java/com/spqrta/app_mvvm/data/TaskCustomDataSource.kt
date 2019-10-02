package com.spqrta.app_mvvm.data

import com.spqrta.common.model.Task
import com.spqrta.common.model.TasksAsyncModel
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject

class TaskCustomDataSource: TasksDataSource {
    override fun getTasks(): Single<List<Task>> {
        //using subject we can adapt any sync or async data retrieval process to RX style
        //also we can easily implement memory caching with BehaviorSubject, see app-rx-mvp-subject module
        //here it is avoided for simplicity
        val subject = BehaviorSubject.create<List<Task>>()

        TasksAsyncModel.INSTANCE.getTasks({
            subject.onNext(it)
            subject.onComplete()
        }, {
            subject.onError(it)
        })

        return subject.singleOrError()
    }

    override fun getTask(id: Int): Single<Task> {
        val subject = BehaviorSubject.create<Task>()

        TasksAsyncModel.INSTANCE.getTask(id, {
            subject.onNext(it)
            subject.onComplete()
        }, {
            subject.onError(it)
        })

        return subject.singleOrError()
    }

    companion object {
        val instance by lazy {
            TaskCustomDataSource()
        }
    }
}