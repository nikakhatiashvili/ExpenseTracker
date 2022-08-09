package com.example.expensetracker.domain.auth

import com.example.expensetracker.R
import com.example.expensetracker.domain.common.ResourceManager
import com.example.expensetracker.domain.common.Result
import com.google.firebase.auth.AuthResult
import java.io.IOException
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val resourceManager: ResourceManager,
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(
        email: String,
        password: String,
        repeatPassword: String
    ): Result<AuthResult> {
        return try {
            if (!email.isNullOrEmpty() &&
                !password.isNullOrEmpty() &&
                !repeatPassword.isNullOrEmpty()
            ) {
                authRepository.signUp(email, password, repeatPassword)
            } else {
                Result.ApiError(null, resourceManager.provide(R.string.All_fields_must_me_full))
            }
        } catch (e: IOException) {
            Result.ApiException(e)
        }
    }
}