package com.example.simple.screens.list

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.simple.MainActivity
import com.example.simple.R
import com.example.simple.repository.TasksRepository
import com.spqrta.common.tasks.TasksAdapter
import com.spqrta.common.tasks.Task
import com.spqrta.common.base.display.BaseFragment
import com.spqrta.common.base.mixins.ErrorToastMixin
import com.spqrta.common.utility.pure.setLinearLayoutManager
import com.spqrta.common.utility.utils.attachProgressbar
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.fragment_list.progressBar


class ListFragment : BaseFragment<MainActivity>(R.layout.fragment_list), ErrorToastMixin {

    private val adapter = TasksAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvTasks.setLinearLayoutManager(requireContext())
        rvTasks.adapter = adapter
        adapter.onItemClickListener = {
            findNavController().navigate(ListFragmentDirections.actionListFragmentToTaskFragment(it.id))
        }

        TasksRepository
                .getTasks()
                .attachProgressbar(progressBar)
                .subscribeManaged({
                    displayTasks(it)
                }, {
                    applyErrorToastMixin(it)
                })
    }

    private fun displayTasks(tasks: List<Task>) {
        adapter.updateItems(tasks)
    }
}