package com.example.ktorapicalling.util

sealed class ApiState<out T> {
    class Empty<out T>: ApiState<T>()
    data class Failure<out T> (val throwable: Throwable): ApiState<T>()
    data class Success<out T> (val data: List<T>): ApiState<T>()
    class Loading<out T> : ApiState<T>()
}