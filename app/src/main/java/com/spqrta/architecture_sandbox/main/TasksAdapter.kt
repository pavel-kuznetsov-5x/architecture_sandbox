package com.spqrta.architecture_sandbox.main

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import com.cucumber007.reusables.recycler.adapters.BaseRecyclerAdapter
import com.spqrta.common.model.Task
import kotlinx.android.synthetic.main.item_task.view.*

class TasksAdapter(context: Context, itemLayout: Int)
    : BaseRecyclerAdapter<Task, TasksAdapter.VhTask>(context, itemLayout) {

    override fun createViewHolder(view: View): VhTask {
        return VhTask(view)
    }

    override fun bindViewHolder(holder: VhTask, task: Task, position: Int) {
        holder.bind(task)
    }

    class VhTask(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(task: Task) {
            itemView.tvName.text = task.name
        }
    }

}