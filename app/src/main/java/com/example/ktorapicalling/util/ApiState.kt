package com.example.ktorapicalling.util

import com.example.ktorapicalling.model.UserResponse

sealed class ApiState {
    object Empty: ApiState()
    class Failure(val throwable: Throwable): ApiState()
    class Success(val data: List<UserResponse>): ApiState()
    object Loading : ApiState()
}