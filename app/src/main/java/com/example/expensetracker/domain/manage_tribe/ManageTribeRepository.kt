package com.example.expensetracker.domain.manage_tribe

import com.example.expensetracker.domain.tasks.Task

interface ManageTribeRepository {

    suspend fun addTask(task: Task)
}
