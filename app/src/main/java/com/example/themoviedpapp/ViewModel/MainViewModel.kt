package com.example.themoviedpapp.ViewModel

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.themoviedpapp.Models.Movie
import com.example.themoviedpapp.Models.MoviesRepository
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviedpapp.*
import com.example.themoviedpapp.View.MainActivity


class MainViewModel (private val repository: MoviesRepository) : ViewModel() {

    val movieList = MutableLiveData<List<Movie>>()
    val errorMessage = MutableLiveData<String>()
    var mainActivity: MainActivity? = null

    @SuppressLint("StaticFieldLeak")
    private var popularMovies: RecyclerView? = null
    private var popularMoviesAdapter : MoviesAdapter? = null
    private lateinit var popularMoviesLayoutMgr: LinearLayoutManager
    var popularMoviesPage = 1

    @SuppressLint("StaticFieldLeak")
    private var topRatedMovies: RecyclerView? = null
    private var topRatedMoviesAdapter : MoviesAdapter? = null
    private lateinit var topRatedMoviesLayoutMgr: LinearLayoutManager
    var topRatedMoviesPage = 1

    @SuppressLint("StaticFieldLeak")
    private var upcomingMovies: RecyclerView? = null
    private var upcomingMoviesAdapter : MoviesAdapter? = null
    private lateinit var upcomingMoviesLayoutMgr: LinearLayoutManager
    var upcomingMoviesPage = 1


    fun getPopularMovies() {
        MoviesRepository.getPopularMovies(
            upcomingMoviesPage,
            ::onUpcomingMoviesFetched,
            ::onError
        )
    }



    private fun onError( context: Context) {
        Toast.makeText(context,"this is toast message",Toast.LENGTH_SHORT).show()
    }




    fun getTopRatedMovies() {
        MoviesRepository.getTopRatedMovies(
            upcomingMoviesPage,
            ::onUpcomingMoviesFetched,
            ::onError
        )
    }


    fun getUpcomingMovies() {
        MoviesRepository.getUpcomingMovies(
            upcomingMoviesPage,
            ::onUpcomingMoviesFetched,
            ::onError
        )
    }






    private fun attachPopularMoviesOnScrollListener() {
        popularMovies?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                // broj filmova u popularMoviesAdapter
                val totalItemCount = popularMoviesLayoutMgr.itemCount
                // filmovi koje trenutno vidimo na ekranu(+ po jedan kojeg ne vidimo sa svake strane)
                val visibleItemCount = popularMoviesLayoutMgr.childCount
                // prvi lijevi item na nasoj listi
                val firstVisibleItem = popularMoviesLayoutMgr.findFirstVisibleItemPosition()
                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    popularMovies?.removeOnScrollListener(this)
                    popularMoviesPage++
                    getPopularMovies()
                }
            }
        })
    }


    fun onPopularMoviesFetched(movies: List<Movie>) {
        popularMoviesAdapter?.appendMovies(movies)
        attachPopularMoviesOnScrollListener()
    }

    private fun attachTopRatedMoviesOnScrollListener() {
        topRatedMovies?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int, context: Context) {

                val totalItemCount = topRatedMoviesLayoutMgr.itemCount
                val visibleItemCount = topRatedMoviesLayoutMgr.childCount
                val firstVisibleItem = topRatedMoviesLayoutMgr.findFirstVisibleItemPosition()

                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    topRatedMovies?.removeOnScrollListener(this)
                    topRatedMoviesPage++
                    getTopRatedMovies()
                }
            }
        })
    }

    fun onTopRatedMoviesFetched(movies: List<Movie>) {
        topRatedMoviesAdapter?.appendMovies(movies)
        attachTopRatedMoviesOnScrollListener()
    }

    private fun attachUpcomingMoviesOnScrollListener() {
        upcomingMovies?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int, context: Context) {

                val totalItemCount = upcomingMoviesLayoutMgr.itemCount
                val visibleItemCount = upcomingMoviesLayoutMgr.childCount
                val firstVisibleItem = upcomingMoviesLayoutMgr.findFirstVisibleItemPosition()

                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    upcomingMovies!!.removeOnScrollListener(this)
                    upcomingMoviesPage++
                    getUpcomingMovies()
                }
            }
        })
    }

    fun onUpcomingMoviesFetched(movies: List<Movie>) {
        upcomingMoviesAdapter?.appendMovies(movies)
        attachUpcomingMoviesOnScrollListener()
    }

    private fun showMovieDetails(movie: Movie, context: Context) {
        val intent = Intent(context, MovieDetails::class.java)
        intent.putExtra(MOVIE_BACKDROP, movie.backdropPath)
        intent.putExtra(MOVIE_POSTER, movie.posterPath)
        intent.putExtra(MOVIE_TITLE, movie.title)
        intent.putExtra(MOVIE_RATING, movie.rating)
        intent.putExtra(MOVIE_RELEASE_DATE, movie.releaseDate)
        intent.putExtra(MOVIE_OVERVIEW, movie.overview)
        context.startActivity(intent)
    }



}