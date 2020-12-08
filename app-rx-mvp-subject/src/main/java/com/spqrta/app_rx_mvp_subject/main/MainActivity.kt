package com.spqrta.app_rx_mvp_subject.main

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.spqrta.app_rx_mvp_subject.R
import com.spqrta.app_rx_mvp_subject.R.id.rvTasks
import com.spqrta.app_rx_mvp_subject.R.id.strLayout
import com.spqrta.app_rx_mvp_subject.task.TaskActivity
import com.spqrta.common.LoadingState
import com.spqrta.common.TasksAdapter
import com.spqrta.common.delegates.*
import com.spqrta.common.model.Task
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainView {

    lateinit var adapter: TasksAdapter
    lateinit var progressIndicatorDelegate: ProgressIndicatorDelegate
    lateinit var toolbarDelegate: ToolbarDelegate
    lateinit var errorDialogDelegate: AlertDialogDelegate

    lateinit var presenter: MainPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressIndicatorDelegate = StrProgressbarDelegate(strLayout)
        toolbarDelegate = AppNameToolbarDelegate(this, toolbar)
        errorDialogDelegate = AlertDialogDelegate(this, "Error")

        adapter = TasksAdapter()
        adapter.onItemClickListener = { item ->
            startActivity(Intent(this, TaskActivity::class.java)
                    .putExtra(Task::class.toString(), item))
        }

        presenter = MainPresenter(this)

        strLayout.setOnRefreshListener {
            presenter.update()
        }

        rvTasks.layoutManager = LinearLayoutManager(this)
        rvTasks.adapter = adapter
    }

    override fun displayTasks(tasks: List<Task>) {
        adapter.updateItems(tasks)
        progressIndicatorDelegate.hide()
    }

    override fun displayState(state: LoadingState) {
        progressIndicatorDelegate.applyState(state)
    }

    override fun displayError(throwable: Throwable) {
        errorDialogDelegate.show(message = throwable.message!!)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.destroy()
    }
}
