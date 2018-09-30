package com.spqrta.app_moxy.task

import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.spqrta.app_moxy.R
import com.spqrta.common.LoadingState
import com.spqrta.common.delegates.*
import com.spqrta.common.model.Task
import kotlinx.android.synthetic.main.activity_task.*

class TaskActivity : MvpAppCompatActivity(), TaskView {

    @InjectPresenter
    internal lateinit var taskPresenter: TaskPresenter

    //todo check
    var taskId: Int? = null
    lateinit var toolbarDelegate: ToolbarDelegate
    lateinit var progressIndicatorDelegate: ProgressIndicatorDelegate
    lateinit var errorDialogDelegate: AlertDialogDelegate


    override fun onCreate(savedInstanceState: Bundle?) {
        val intentTask: Task = intent.getParcelableExtra(Task::class.toString())!!
        taskId = intentTask.id

        //todo check
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)

        toolbarDelegate = TextToolbarDelegate(this, toolbar, intentTask.name)
        progressIndicatorDelegate = HideableProgressbarDelegate(progressBar)
        errorDialogDelegate = AlertDialogDelegate(this, "Error")

    }

    override fun displayTask(task: Task) {
        toolbarDelegate.setTitle(task.name)
        tvDescription.text = task.description
    }

    override fun displayError(throwable: Throwable) {
        errorDialogDelegate.show(message = throwable.message!!)
    }

    override fun displayState(loadingState: LoadingState) {
        progressIndicatorDelegate.applyState(loadingState)
    }

    @ProvidePresenter
    fun provideTaskPresenter() = TaskPresenter(taskId!!)
}
