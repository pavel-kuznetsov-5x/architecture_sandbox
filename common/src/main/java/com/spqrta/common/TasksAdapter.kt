package com.spqrta.common

import android.content.Context
import android.view.View
import android.widget.AdapterView
import com.spqrta.common.model.Task
import com.spqrta.reusables.utility.recycler.BaseAdapter
import kotlinx.android.synthetic.main.item_task.view.*

class TasksAdapter() : BaseAdapter<Task, TasksAdapter.VhTask>() {

    override val itemLayoutResource: Int = R.layout.item_task

    override fun createViewHolder(view: View, baseClickListener: (Int) -> Unit): VhTask {
        return VhTask(view, baseClickListener)
    }

    class VhTask(itemView: View, clickListener: ((Int) -> Unit)) : BaseVh<Task>(itemView, clickListener) {
        override fun bind(task: Task) {
            itemView.tvName.text = task.name
        }
    }

}