package com.example.month_4.ui.new_task

import android.annotation.SuppressLint
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.month_4.databinding.ItemTaskBinding

class TaskAdapter(private var tasks: MutableList<TaskModel>):
    RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    var onDelete: ((Int) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun renew(newList: List<TaskModel>) {
        tasks = newList.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemTaskBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun getItemCount(): Int = tasks.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(tasks[position])

    }

    inner class ViewHolder(private val binding: ItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(task: TaskModel) {
            binding.tvTitle.text = task.title
            binding.tvDesc.text = task.description

            binding.root.setOnLongClickListener {
                onDelete?.invoke(task.id)
                true
            }

        }
    }
}

