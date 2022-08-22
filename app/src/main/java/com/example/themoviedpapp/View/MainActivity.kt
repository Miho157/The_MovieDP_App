package com.example.themoviedpapp.View

import MyViewModelFactory
import ViewModel.MainViewModel
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviedpapp.*
import com.example.themoviedpapp.Models.Api
import com.example.themoviedpapp.Models.Movie
import com.example.themoviedpapp.Models.MoviesRepository
import com.example.themoviedpapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private lateinit var binding: ActivityMainBinding

    lateinit var viewModel: MainViewModel

    private val retrofitService = Api.getInstance()
    val adapter = MoviesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //get viewmodel instance using MyViewModelFactory
        viewModel =
            ViewModelProvider(this, MyViewModelFactory(MoviesRepository)).get(
                MainViewModel::class.java
            )

        //set recyclerview adapter
        binding.popularMovies.adapter = adapter
        binding.topRatedMovies.adapter = adapter
        binding.upcomingMovies.adapter = adapter

        viewModel.movieList.observe(this, Observer {
            Log.d(TAG, "movieList: $it")
            adapter.appendMovies(it)
        })


        viewModel.errorMessage.observe(this, Observer {
            Log.d(TAG, "errorMessage: $it")
        })

        viewModel.getPopularMovies()
        viewModel.getTopRatedMovies()
        viewModel.getUpcomingMovies()
        
    }
}