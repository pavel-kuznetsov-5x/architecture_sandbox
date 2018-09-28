package com.spqrta.app_moxy.main

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.PresenterType
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.spqrta.app_moxy.R
import com.spqrta.app_moxy.task.TaskActivity
import com.spqrta.common.ProgressbarDelegate
import com.spqrta.common.StrProgressbarDelegate
import com.spqrta.common.Task
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : MvpAppCompatActivity(), MainView {

    @InjectPresenter(type = PresenterType.GLOBAL)
    internal lateinit var mainPresenter: MainPresenter

    lateinit var adapter: TasksAdapter
    lateinit var progressbarDelegate: ProgressbarDelegate

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

    @ProvidePresenter
    fun provideMainPresenter() = MainPresenter()

}
