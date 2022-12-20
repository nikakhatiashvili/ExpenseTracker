package com.example.expensetracker.presentation.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.expensetracker.databinding.TaskBinding
import com.example.expensetracker.domain.tasks.Task


class TasksAdapter : RecyclerView.Adapter<TasksAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksAdapter.ViewHolder {
        return ViewHolder(
            TaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    var data: List<Task> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ViewHolder(private val binding:TaskBinding):RecyclerView.ViewHolder(binding.root){
        private lateinit var currentData:Task
        fun bind(){
            currentData = data[adapterPosition]
            binding.name.text = currentData.name
            binding.txtDescription.text = currentData.description
        }
    }

    override fun onBindViewHolder(holder: TasksAdapter.ViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount()= data.size

}
