package ViewModel

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviedpapp.*
import com.example.themoviedpapp.Models.Movie
import com.example.themoviedpapp.Models.MoviesRepository
import androidx.lifecycle.MutableLiveData



class MainViewModel (private val repository: MoviesRepository) : ViewModel() {

    val movieList = MutableLiveData<List<Movie>>()
    val errorMessage = MutableLiveData<String>()


    private var popularMovies: RecyclerView? = null
    private var popularMoviesAdapter : MoviesAdapter? = null
    private lateinit var popularMoviesLayoutMgr: LinearLayoutManager
    private var popularMoviesPage = 1

    private var topRatedMovies: RecyclerView? = null
    private var topRatedMoviesAdapter : MoviesAdapter? = null
    private lateinit var topRatedMoviesLayoutMgr: LinearLayoutManager
    private var topRatedMoviesPage = 1

    private var upcomingMovies: RecyclerView? = null
    private var upcomingMoviesAdapter : MoviesAdapter? = null
    private lateinit var upcomingMoviesLayoutMgr: LinearLayoutManager
    private var upcomingMoviesPage = 1



    fun getPopularMovies() {
        MoviesRepository.getPopularMovies(
            popularMoviesPage,
            ::onPopularMoviesFetched,
            ::onError
        )
    }

    private fun onPopularMoviesFetched(movies: List<Movie>) {
        popularMoviesAdapter?.appendMovies(movies)
        attachPopularMoviesOnScrollListener()
    }
    private fun onError( context: Context) {
        Toast.makeText(context,"this is toast message",Toast.LENGTH_SHORT).show()
    }



    private fun attachPopularMoviesOnScrollListener() {
        popularMovies?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int, context: Context) {
                popularMovies?.setLayoutManager(LinearLayoutManager(context));
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


    fun getTopRatedMovies() {
        MoviesRepository.getTopRatedMovies(
            topRatedMoviesPage,
            ::onTopRatedMoviesFetched,
            ::onError
        )
    }
    private fun attachTopRatedMoviesOnScrollListener() {
        topRatedMovies?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int, context: Context) {
                topRatedMovies?.setLayoutManager(LinearLayoutManager(context));
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

    private fun onTopRatedMoviesFetched(movies: List<Movie>) {
        topRatedMoviesAdapter?.appendMovies(movies)
        attachTopRatedMoviesOnScrollListener()
    }

    fun getUpcomingMovies() {
        MoviesRepository.getUpcomingMovies(
            upcomingMoviesPage,
            ::onUpcomingMoviesFetched,
            ::onError
        )
    }

    private fun attachUpcomingMoviesOnScrollListener() {
        upcomingMovies?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int, context: Context) {
                upcomingMovies?.setLayoutManager(LinearLayoutManager(context));
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

    private fun onUpcomingMoviesFetched(movies: List<Movie>) {
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