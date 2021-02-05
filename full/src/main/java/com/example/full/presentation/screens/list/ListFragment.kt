package com.example.full.presentation.screens.list

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.full.MainActivity
import com.example.full.R
import com.example.full.data.repository.TasksRepository
import com.spqrta.common.TasksAdapter
import com.spqrta.common.model.Task
import com.spqrta.reusables.base.display.BaseFragment
import com.spqrta.reusables.base.mixins.ErrorToastMixin
import com.spqrta.reusables.utility.pure.setLinearLayoutManager
import com.spqrta.reusables.utility.utils.attachProgressbar
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