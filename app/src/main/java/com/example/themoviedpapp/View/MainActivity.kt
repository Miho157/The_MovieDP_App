package com.example.themoviedpapp.View

import com.example.themoviedpapp.ViewModel.MyViewModelFactory
import com.example.themoviedpapp.ViewModel.MainViewModel
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.themoviedpapp.*
import com.example.themoviedpapp.Models.Api
import com.example.themoviedpapp.Models.MoviesRepository
import com.example.themoviedpapp.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*


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


        //get viewmodel instance using com.example.themoviedpapp.ViewModel.MyViewModelFactory
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

        popular_movies.layoutManager = LinearLayoutManager(this)
        top_rated_movies.layoutManager = LinearLayoutManager(this)
        upcoming_movies.layoutManager = LinearLayoutManager(this)


        viewModel.errorMessage.observe(this, Observer {
            Log.d(TAG, "errorMessage: $it")
        })

        viewModel.getPopularMovies()
        viewModel.getTopRatedMovies()
        viewModel.getUpcomingMovies()


    }
}