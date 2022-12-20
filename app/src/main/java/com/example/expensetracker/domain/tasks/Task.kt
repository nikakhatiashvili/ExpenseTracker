package com.example.expensetracker.domain.tasks

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Task(
    val completed:Boolean? = false,
    val description:String? = "",
    val name:String? = ""
):Parcelable
