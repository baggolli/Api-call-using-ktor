package com.example.ktorapicalling.model

data class UserResponse(
    val email: String,
    val id: Int,
    val name: String,
    val phone: String,
    val username: String,
    val website: String
)
