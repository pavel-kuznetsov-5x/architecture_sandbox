package com.example.full.presentation.screens.list

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.full.MainActivity
import com.example.full.R
import com.example.full.presentation.common.ViewModelFactory
import com.spqrta.common.base.display.BaseFragment
import com.spqrta.common.base.display.JustInitial
import com.spqrta.common.base.mixins.ProgressbarMixin
import com.spqrta.common.tasks.Task
import com.spqrta.common.tasks.TasksAdapter
import com.spqrta.common.utility.pure.setLinearLayoutManager
import kotlinx.android.synthetic.main.fragment_list.*


class ListFragment : BaseFragment<MainActivity>(R.layout.fragment_list), ProgressbarMixin {

    private lateinit var vm: ListViewModel
    private val adapter = TasksAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm = ViewModelProvider(this, ViewModelFactory).get(ListViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm.init()

        vm.state.observe(viewLifecycleOwner, { state ->
            applyErrorToastMixin(state)
            applyProgressbarMixin(state, progressBar)
            when(state) {
                is JustInitial -> {
                    initViews()
                }
                is ListViewModel.Success -> {
                    displayTasks(state.tasks)
                }
            }
        })
    }

    private fun initViews() {
        rvTasks.setLinearLayoutManager(requireContext())
        rvTasks.adapter = adapter
        adapter.onItemClickListener = {
            findNavController().navigate(ListFragmentDirections.actionListFragmentToTaskFragment(it.id))
        }
    }

    private fun displayTasks(tasks: List<Task>) {
        adapter.updateItems(tasks)
    }
}