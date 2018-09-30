package com.spqrta.app_rx_mvp_subject.task

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.spqrta.app_rx_mvp_subject.R
import com.spqrta.common.delegates.*
import com.spqrta.common.model.Task
import com.spqrta.common.model.TasksModel
import kotlinx.android.synthetic.main.activity_task.*

class TaskActivity : AppCompatActivity() {

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

        progressIndicatorDelegate.show()
        TasksModel.INSTANCE.getTask(intentTask.id, { loadedTask ->
            task = loadedTask
            displayTask(loadedTask)
            progressIndicatorDelegate.hide()
        }, {
            displayError(it)
            progressIndicatorDelegate.hide()
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
