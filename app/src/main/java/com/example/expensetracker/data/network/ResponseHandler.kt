package com.example.expensetracker.data.network

import retrofit2.HttpException
import com.example.expensetracker.common.Result
import retrofit2.Response
import java.io.IOException

class ResponseHandler {

    fun <T> handleApi(execute: () -> Response<T>): Result<T> {
        return try {
            val response = execute()
            val body = response.body()
            if (response.isSuccessful && body != null) {
                Result.ApiSuccess(body)
            } else {
                Result.ApiError(code = response.code(), message = response.message())
            }
        } catch (e: HttpException) {
            Result.ApiError(code = e.code(), message = e.message())
        } catch (e: IOException) {
            Result.ApiException(e)
        }
    }
}
