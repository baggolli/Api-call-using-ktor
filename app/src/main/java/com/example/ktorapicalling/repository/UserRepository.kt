package com.example.ktorapicalling.repository

import com.example.ktorapicalling.model.UserResponse
import com.example.ktorapicalling.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class UserRepository @Inject constructor(private val apiService: ApiService) {
    fun getUsers(): Flow<List<UserResponse>> = flow {
        emit(apiService.getUsersData())
    }.flowOn(Dispatchers.IO)
}