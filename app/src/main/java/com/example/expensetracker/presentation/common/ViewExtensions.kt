package com.example.expensetracker.presentation.common

import android.content.Context
import android.view.View
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout

fun View.gone() {
    visibility = View.GONE
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun TextInputLayout.toStringTrim():String {
   return this.editText?.text.toString().trim()
}
fun Context.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}
