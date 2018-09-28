package com.spqrta.app_rx_mvp_subject.main

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.spqrta.app_rx_mvp_subject.R
import com.spqrta.app_rx_mvp_subject.task.TaskActivity
import com.spqrta.common.ProgressbarDelegate
import com.spqrta.common.StrProgressbarDelegate
import com.spqrta.common.Task
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainView {

    lateinit var adapter: TasksAdapter
    lateinit var progressbarDelegate: ProgressbarDelegate

    lateinit var presenter: MainPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = getString(R.string.app_name)

        progressbarDelegate = StrProgressbarDelegate(strLayout)

        adapter = TasksAdapter(this, R.layout.item_task)
        adapter.setItemClickListener { position, view, item ->
            startActivity(Intent(this, TaskActivity::class.java)
                    .putExtra(Task::class.toString(), item as Task))
        }

        presenter = MainPresenter(this)

        strLayout.setOnRefreshListener {
            presenter.update()
        }

        rvTasks.layoutManager = LinearLayoutManager(this)
        rvTasks.adapter = adapter
    }

    override fun displayTasks(tasks: List<Task>) {
        adapter.setItemsAndUpdate(tasks)
        progressbarDelegate.hide()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.destroy()
    }
}
