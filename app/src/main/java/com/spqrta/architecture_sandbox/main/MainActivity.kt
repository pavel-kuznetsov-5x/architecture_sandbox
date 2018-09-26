package com.spqrta.architecture_sandbox.main

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.spqrta.architecture_sandbox.R
import com.spqrta.architecture_sandbox.common.Task
import com.spqrta.architecture_sandbox.model.TasksModel
import com.spqrta.architecture_sandbox.task.TaskActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = getString(R.string.app_name)

        val adapter = TasksAdapter(this, R.layout.item_task)
        adapter.setItemClickListener { position, view, item ->
            startActivity(Intent(this, TaskActivity::class.java)
                    .putExtra(Task::class.toString(), item as Task))
        }

        onStartLoading()
        TasksModel.instance.getTasks { tasks ->
            adapter.setItemsAndUpdate(tasks)
            onStopLoading()
        }

        rvTasks.layoutManager = LinearLayoutManager(this)
        rvTasks.adapter = adapter
    }

    private fun onStartLoading() {
        progressBar.visibility = View.VISIBLE
    }

    private fun onStopLoading() {
        progressBar.visibility = View.GONE
    }
}
