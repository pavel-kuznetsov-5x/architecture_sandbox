package com.spqrta.app_mvvm.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.spqrta.app_mvvm.task.TaskActivity
import com.spqrta.architecture_sandbox.R
import com.spqrta.common.delegates.*
import com.spqrta.common.model.Task
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var tasksViewModel: TasksViewModel

    lateinit var progressIndicatorDelegate: ProgressIndicatorDelegate
    lateinit var toolbarDelegate: ToolbarDelegate
    lateinit var errorDialogDelegate: AlertDialogDelegate


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressIndicatorDelegate = StrProgressbarDelegate(strLayout)
        toolbarDelegate = AppNameToolbarDelegate(this, toolbar)
        errorDialogDelegate = AlertDialogDelegate(this, "Error")

        val adapter = TasksAdapter(this, R.layout.item_task)
        adapter.setItemClickListener { position, view, item ->
            startActivity(Intent(this, TaskActivity::class.java)
                    .putExtra(Task::class.toString(), item as Task))
        }

        rvTasks.layoutManager = LinearLayoutManager(this)
        rvTasks.adapter = adapter

        tasksViewModel = ViewModelProviders.of(this).get(TasksViewModel::class.java)

        tasksViewModel.stateLiveData.observe(this, Observer { state ->
            progressIndicatorDelegate.applyState(state!!)
        })

        tasksViewModel.tasksLiveData.observe(this, Observer { tasks ->
            adapter.setItemsAndUpdate(tasks)
        })

        tasksViewModel.errorLiveEvent.observe(this, Observer { throwable ->
            errorDialogDelegate.show(message = throwable!!.message!!)
        })

        strLayout.setOnRefreshListener {
            progressIndicatorDelegate.show()
            tasksViewModel.update()
        }
    }
}
