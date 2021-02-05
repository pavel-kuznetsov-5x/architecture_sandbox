package com.example.full.presentation.screens.task

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.example.full.MainActivity
import com.example.full.R
import com.example.full.data.repository.TasksRepository
import com.spqrta.common.tasks.Task
import com.spqrta.reusables.base.display.BaseFragment
import com.spqrta.reusables.utility.pure.toView
import com.spqrta.reusables.utility.utils.attachProgressbar
import kotlinx.android.synthetic.main.fragment_task.*

class TaskFragment : BaseFragment<MainActivity>(R.layout.fragment_task) {

    private val args: TaskFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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