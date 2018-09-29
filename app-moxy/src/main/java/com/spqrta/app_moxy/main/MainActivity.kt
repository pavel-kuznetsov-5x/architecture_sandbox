package com.spqrta.app_moxy.main

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.spqrta.app_moxy.R
import com.spqrta.app_moxy.task.TaskActivity
import com.spqrta.common.*
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : MvpAppCompatActivity(), MainView {

    @InjectPresenter
    internal lateinit var mainPresenter: MainPresenter

    lateinit var progressbarDelegate: ProgressbarDelegate
    lateinit var toolbarDelegate: ToolbarDelegate
    lateinit var adapter: TasksAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressbarDelegate = StrProgressbarDelegate(strLayout)
        toolbarDelegate = AppNameToolbarDelegate(this, toolbar)

        adapter = TasksAdapter(this, R.layout.item_task)
        adapter.setItemClickListener { position, view, item ->
            startActivity(Intent(this, TaskActivity::class.java)
                    .putExtra(Task::class.toString(), item as Task))
        }

        strLayout.setOnRefreshListener {
            mainPresenter.update()
        }

        rvTasks.layoutManager = LinearLayoutManager(this)
        rvTasks.adapter = adapter
    }

    override fun displayTasks(tasks: List<Task>) {
        adapter.setItemsAndUpdate(tasks)
        progressbarDelegate.hide()
    }

    override fun displayState(state: LoadingState) {
        progressbarDelegate.applyState(state)
    }

    @ProvidePresenter
    fun provideMainPresenter() = MainPresenter()

}
