package com.example.expensetracker.domain.auth

import com.example.expensetracker.domain.common.Result
import com.google.firebase.auth.AuthResult

interface AuthRepository {

    suspend fun signUp(email: String, password: String, repeatPassword: String): Result<AuthResult>

    suspend fun signIn(email: String, password: String): Result<AuthResult>
}
