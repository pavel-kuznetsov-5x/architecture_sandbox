package com.spqrta.app_rx.task

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.spqrta.architecture_sandbox.R
import com.spqrta.common.delegates.TextToolbarDelegate
import com.spqrta.common.delegates.ToolbarDelegate
import com.spqrta.common.model.Task
import kotlinx.android.synthetic.main.activity_task.*

class TaskActivity : AppCompatActivity() {

    lateinit var task: Task
    lateinit var toolbarDelegate: ToolbarDelegate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)

        task = intent.getParcelableExtra(Task::class.toString())!!
        toolbarDelegate = TextToolbarDelegate(this, toolbar, task.name)


    }
}
