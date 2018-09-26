package com.spqrta.architecture_sandbox.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.spqrta.architecture_sandbox.R
import com.spqrta.architecture_sandbox.Task
import com.spqrta.architecture_sandbox.task.TaskActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var tasksViewModel: TasksViewModel

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

        rvTasks.layoutManager = LinearLayoutManager(this)
        rvTasks.adapter = adapter

        tasksViewModel = ViewModelProviders.of(this).get(TasksViewModel::class.java)
        tasksViewModel.tasksLiveData.observe(this, Observer {
            tasks -> adapter.setItemsAndUpdate(tasks)
        })

    }
}
