package com.example.ktorapicalling.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ktorapicalling.repository.UserRepository
import com.example.ktorapicalling.util.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(val repository: UserRepository) : ViewModel() {

    private val apiStateFlow: MutableStateFlow<ApiState> = MutableStateFlow(ApiState.Empty)
    val getApiState: StateFlow<ApiState> = apiStateFlow

    fun getUsers() = viewModelScope.launch {
        repository.getUsers()
            .onStart { apiStateFlow.value = ApiState.Loading }
            .catch { exception -> apiStateFlow.value = ApiState.Failure(exception) }
            .collect { response -> apiStateFlow.value = ApiState.Success(response) }
    }
}