package com.example.expensetracker.data

import com.example.expensetracker.R
import com.example.expensetracker.domain.auth.AuthDataStore
import com.example.expensetracker.domain.auth.AuthRepository
import com.example.expensetracker.domain.common.ResourceManager
import com.example.expensetracker.domain.common.Result
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebase: FirebaseAuth,
    private val resourceManager: ResourceManager,
    private val authDataStore: AuthDataStore
) : AuthRepository {

    override suspend fun signUp(
        email: String,
        password: String,
        repeatPassword: String
    ): Result<AuthResult> {
        return try {
            if (password == repeatPassword) {
                val data = firebase.createUserWithEmailAndPassword(email, password).await()
                saveUid(data.user?.uid.toString())
                Result.ApiSuccess(data)
            } else {
                Result.ApiError(null, resourceManager.provide(R.string.passwords_do_not_match))
            }
        } catch (e: Exception) {
            Result.ApiError(null, e.message.toString())
        }
    }

    override suspend fun signIn(email: String, password: String): Result<AuthResult> {
        return try {
            val data = firebase.signInWithEmailAndPassword(email, password).await()
            saveUid(data.user?.uid.toString())
            Result.ApiSuccess(data)
        } catch (e: Exception) {
            Result.ApiError(null, e.message.toString())
        }
    }

    private suspend fun saveUid(uid: String) {
        authDataStore.saveToken(uid)
    }
}