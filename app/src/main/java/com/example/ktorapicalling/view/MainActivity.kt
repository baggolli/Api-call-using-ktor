package com.example.ktorapicalling.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ktorapicalling.adapter.UserAdapter
import com.example.ktorapicalling.databinding.ActivityMainBinding
import com.example.ktorapicalling.util.ApiState
import com.example.ktorapicalling.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val userViewModel: UserViewModel by viewModels()

    @Inject
    lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()

        userViewModel.getUsers()
        lifecycleScope.launch {
            userViewModel.getApiState.collect {
                binding.apply {
                    when (it) {
                        is ApiState.Failure -> {
                            recyclerView.visibility = View.GONE
                            progressBar.visibility = View.GONE
                            errorTextView.visibility = View.VISIBLE
                            errorTextView.text = it.throwable.toString()
                        }
                        is ApiState.Success -> {
                            recyclerView.visibility = View.VISIBLE
                            progressBar.visibility = View.GONE
                            userAdapter.submitList(it.data)
                        }
                        is ApiState.Loading -> {
                            progressBar.visibility = View.VISIBLE
                        }
                    }
                }
            }
        }
    }

    private fun initRecyclerView() {
        binding.apply {
            recyclerView.apply {
                addItemDecoration(
                    DividerItemDecoration(
                        this@MainActivity,
                        (recyclerView.layoutManager as LinearLayoutManager).orientation
                    )
                )
                adapter = userAdapter
            }
        }
    }
}