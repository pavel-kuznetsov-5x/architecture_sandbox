package com.example.full.presentation.screens.task

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.full.MainActivity
import com.example.full.R
import com.example.full.data.repository.TasksRepository
import com.example.full.presentation.common.ViewModelFactory
import com.spqrta.common.base.display.BaseFragment
import com.spqrta.common.tasks.Task
import com.spqrta.common.utility.pure.toView
import com.spqrta.common.utility.utils.attachProgressbar
import kotlinx.android.synthetic.main.fragment_task.*

class TaskFragment : BaseFragment<MainActivity>(R.layout.fragment_task) {

    private lateinit var vm: TaskViewModel
    private val args: TaskFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm = ViewModelProvider(this, ViewModelFactory).get(TaskViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm.init(args.taskId)

        vm.state.observe(viewLifecycleOwner, { state ->
            applyErrorToastMixin(state)
            applyProgressbarMixin(state, progressBar)
            when(state) {
                is TaskViewModel.Success -> {
                    displayTask(state.task)
                }
            }
        })

        TasksRepository
                .getTask(args.taskId)
                .attachProgressbar(progressBar)
                .subscribeManaged({
                    displayTask(it)
                }, {
                    applyErrorToastMixin(it)
                })
    }

    private fun displayTask(task: Task) {
        task.name.toView(tvName)
        task.description.toView(tvDescription)
    }
}