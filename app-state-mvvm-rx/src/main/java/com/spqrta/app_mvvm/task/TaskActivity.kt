package com.spqrta.app_mvvm.task

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.spqrta.app_mvvm.JustError
import com.spqrta.app_mvvm.JustLoading
import com.spqrta.architecture_sandbox.R
import com.spqrta.common.delegates.*
import com.spqrta.common.tasks.Task
import kotlinx.android.synthetic.main.activity_task.*

class TaskActivity : AppCompatActivity() {

    lateinit var model: TaskViewModel

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
        errorDialogDelegate = AlertDialogDelegate(this, "Error",
                positiveButtonText = "Go back",
                positiveAction = {
                    it.dismiss()
                    onBackPressed()
                }
        )

        model = ViewModelProviders.of(this, TaskViewModelFactory(intentTask.id))
                .get(TaskViewModel::class.java)

        model.state.observe(this, Observer { state ->
            when (state) {
                is JustLoading -> {
                    progressIndicatorDelegate.show()
                }
                is TaskViewModel.Success -> {
                    progressIndicatorDelegate.hide()
                    displayTask(state.task)
                }
                is JustError -> {
                    progressIndicatorDelegate.hide()
                    errorDialogDelegate.show(message = state.exception.message ?: "Error")
                }
                else -> throw IllegalStateException()
            }
        })
    }

    fun displayTask(task: Task) {
        toolbarDelegate.setTitle(task.name)
        tvDescription.text = task.description
    }

}
