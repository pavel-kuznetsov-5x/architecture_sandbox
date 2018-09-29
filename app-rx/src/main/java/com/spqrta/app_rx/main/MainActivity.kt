package com.spqrta.app_rx.main

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.spqrta.app_rx.model.TasksModel
import com.spqrta.app_rx.task.TaskActivity
import com.spqrta.architecture_sandbox.R
import com.spqrta.common.Task
import com.spqrta.common.delegates.*
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var adapter: TasksAdapter
    lateinit var progressbarDelegate: ProgressbarDelegate
    lateinit var toolbarDelegate: ToolbarDelegate
    lateinit var errorDialogDelegate: AlertDialogDelegate

    var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressbarDelegate = StrProgressbarDelegate(strLayout)
        toolbarDelegate = AppNameToolbarDelegate(this, toolbar)
        errorDialogDelegate = AlertDialogDelegate(this, "Error")

        adapter = TasksAdapter(this, R.layout.item_task)
        adapter.setItemClickListener { position, view, item ->
            startActivity(Intent(this, TaskActivity::class.java)
                    .putExtra(Task::class.toString(), item as Task))
        }

        loadTasks()

        strLayout.setOnRefreshListener {
            loadTasks()
        }

        rvTasks.layoutManager = LinearLayoutManager(this)
        rvTasks.adapter = adapter
    }

    private fun loadTasks() {
        progressbarDelegate.show()
        disposable?.dispose()
        disposable = TasksModel.INSTANCE.getTasks()
                .subscribe({ tasks ->
                    adapter.setItemsAndUpdate(tasks)
                    progressbarDelegate.hide()
                }, {
                    progressbarDelegate.hide()
                    errorDialogDelegate.show(message = it.message!!)
                })
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
    }
}
