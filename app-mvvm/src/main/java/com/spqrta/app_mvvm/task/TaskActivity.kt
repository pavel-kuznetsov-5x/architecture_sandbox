package com.spqrta.app_mvvm.task

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.spqrta.architecture_sandbox.R
import com.spqrta.common.Task
import kotlinx.android.synthetic.main.activity_task.*

class TaskActivity : AppCompatActivity() {

    lateinit var task: Task

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)
        setSupportActionBar(toolbar)

        task = intent.getParcelableExtra(Task::class.toString())!!
        supportActionBar!!.title = task.name


    }
}