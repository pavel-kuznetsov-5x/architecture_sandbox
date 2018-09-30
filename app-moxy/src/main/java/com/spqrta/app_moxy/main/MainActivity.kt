package com.spqrta.app_moxy.main

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.spqrta.app_moxy.R
import com.spqrta.app_moxy.task.TaskActivity
import com.spqrta.common.LoadingState
import com.spqrta.common.delegates.*
import com.spqrta.common.model.Task
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : MvpAppCompatActivity(), MainView {

    @InjectPresenter
    internal lateinit var mainPresenter: MainPresenter

    lateinit var adapter: TasksAdapter
    lateinit var progressIndicatorDelegate: ProgressIndicatorDelegate
    lateinit var toolbarDelegate: ToolbarDelegate
    lateinit var errorDialogDelegate: AlertDialogDelegate


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressIndicatorDelegate = StrProgressbarDelegate(strLayout)
        toolbarDelegate = AppNameToolbarDelegate(this, toolbar)
        errorDialogDelegate = AlertDialogDelegate(this, "Error")

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
        progressIndicatorDelegate.hide()
    }

    override fun displayState(loadingState: LoadingState) {
        progressIndicatorDelegate.applyState(loadingState)
    }

    override fun displayError(throwable: Throwable) {
        errorDialogDelegate.show(message = throwable.message!!)
    }

    @ProvidePresenter
    fun provideMainPresenter() = MainPresenter()

}
