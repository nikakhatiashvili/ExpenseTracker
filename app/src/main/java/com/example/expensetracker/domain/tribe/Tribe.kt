package com.example.expensetracker.domain.tribe

import com.example.expensetracker.domain.tasks.Task

data class Tribe(
    val name:String?= null,
    val inviteId:String? = null,
    val members:List<String>?= null,
    val task:List<Task>?= null,
)
