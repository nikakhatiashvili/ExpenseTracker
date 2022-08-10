package com.example.expensetracker.presentation.common

import android.view.View
import android.widget.EditText
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