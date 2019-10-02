package com.spqrta.app_mvvm.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.spqrta.app_mvvm.JustError
import com.spqrta.app_mvvm.JustLoading
import com.spqrta.app_mvvm.task.TaskActivity
import com.spqrta.architecture_sandbox.R
import com.spqrta.common.delegates.*
import com.spqrta.common.model.Task
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var model: TasksViewModel

    lateinit var progressIndicatorDelegate: ProgressIndicatorDelegate
    lateinit var toolbarDelegate: ToolbarDelegate
    lateinit var errorDialogDelegate: AlertDialogDelegate

    lateinit var adapter: TasksAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressIndicatorDelegate = StrProgressbarDelegate(strLayout)
        toolbarDelegate = AppNameToolbarDelegate(this, toolbar)
        errorDialogDelegate = AlertDialogDelegate(this, "Error", positiveAction = {
            it.dismiss()
            model.update()
        }, positiveButtonText = "Refresh")

        model = ViewModelProviders.of(this, TasksViewModelFactory()).get(TasksViewModel::class.java)

        model.state.observe(this, Observer { state ->
            when(state) {
                is JustLoading -> {
                    progressIndicatorDelegate.show()
                }
                is TasksViewModel.Success -> {
                    progressIndicatorDelegate.hide()
                    adapter.setItemsAndUpdate(state.tasks)
                }
                is JustError -> {
                    progressIndicatorDelegate.hide()
                    errorDialogDelegate.show(message = state.exception.message ?: "Error")
                }
                else -> throw IllegalStateException()
            }
        })

        adapter = TasksAdapter(this, R.layout.item_task)
        adapter.setItemClickListener { position, view, item ->
            startActivity(Intent(this, TaskActivity::class.java)
                    .putExtra(Task::class.toString(), item as Task))
        }

        rvTasks.layoutManager = LinearLayoutManager(this)
        rvTasks.adapter = adapter

        strLayout.setOnRefreshListener {
            model.update()
        }
    }
}
