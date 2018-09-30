package com.spqrta.app_mvvm.task

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.spqrta.architecture_sandbox.R
import com.spqrta.common.delegates.*
import com.spqrta.common.model.Task
import kotlinx.android.synthetic.main.activity_task.*

class TaskActivity : AppCompatActivity() {

    lateinit var taskViewModel: TaskViewModel

    lateinit var task: Task
    lateinit var toolbarDelegate: ToolbarDelegate
    lateinit var progressIndicatorDelegate: ProgressIndicatorDelegate
    lateinit var errorDialogDelegate: AlertDialogDelegate


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)

        val intentTask: Task = intent.getParcelableExtra(Task::class.toString())!!
        toolbarDelegate = TextToolbarDelegate(this, toolbar, intentTask.name)
        progressIndicatorDelegate = HideableProgressbarDelegate(progressBar)
        errorDialogDelegate = AlertDialogDelegate(this, "Error")

        taskViewModel = ViewModelProviders.of(this, TaskViewModelFactory(intentTask.id))
                .get(TaskViewModel::class.java)

        taskViewModel.stateLiveData.observe(this, Observer { state ->
            progressIndicatorDelegate.applyState(state!!)
        })

        taskViewModel.taskLiveData.observe(this, Observer { task ->
            displayTask(task!!)
        })

        taskViewModel.errorLiveEvent.observe(this, Observer { throwable ->
            displayError(throwable!!)
        })

    }

    fun displayTask(task: Task) {
        toolbarDelegate.setTitle(task.name)
        tvDescription.text = task.description
    }

    fun displayError(throwable: Throwable) {
        errorDialogDelegate.show(message = throwable.message!!)
    }
}
