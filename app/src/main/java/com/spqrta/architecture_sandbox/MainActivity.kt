package com.spqrta.architecture_sandbox

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = TasksAdapter(this, R.layout.item_task)
        adapter.setItemClickListener({ position, view, item ->
            startActivity(Intent(this, TaskActivity::class.java)
                    .putExtra(Task::class.toString(), item as Task))
        })
        adapter.setItemsAndUpdate(TasksModel.instance.getTasks())
        rvTasks.layoutManager = LinearLayoutManager(this)
        rvTasks.adapter = adapter
    }
}
