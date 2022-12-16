package com.example.expensetracker.domain.tasks

data class Task(
    val completed:Boolean? = false,
    val description:String? = "",
    val name:String? = ""
)
