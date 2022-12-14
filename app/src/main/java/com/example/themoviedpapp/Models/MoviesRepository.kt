package com.example.themoviedpapp.Models

import android.content.Context
import android.util.Log
import com.example.themoviedpapp.GetMovieResponses
import com.example.themoviedpapp.View.MainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.reflect.KFunction1
import kotlin.reflect.KFunction2

object MoviesRepository {

    private val api: Api
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        api = retrofit.create(Api::class.java)
    }


    // popularni filmovi
    fun getPopularMovies(
        page: Int = 1,
        onSuccess: (movies: List<Movie>) -> Unit,
        onError: KFunction1<Context, Unit>
    ) {
        api.getPopularMovies(page = page)
            .enqueue(object : Callback<GetMovieResponses> {
                override fun onResponse(
                    call: Call<GetMovieResponses>,
                    response: Response<GetMovieResponses>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()

                        if (responseBody != null) {
                            onSuccess.invoke(responseBody.movies)
                        } else {
                            onError.invoke()
                        }
                    } else {
                        onError.invoke()
                    }
                }

                override fun onFailure(call: Call<GetMovieResponses>, t: Throwable) {
                    onError.invoke()
                }
            })
    }

    //top rated filmovi
    fun getTopRatedMovies(
        page: Int = 1,
        onSuccess: (movies: List<Movie>) -> Unit,
        onError: KFunction1<Context, Unit>
    ) {
        api.getTopRatedMovies(page = page)
            .enqueue(object : Callback<GetMovieResponses> {
                override fun onResponse(
                    call: Call<GetMovieResponses>,
                    response: Response<GetMovieResponses>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()

                        if (responseBody != null) {
                            onSuccess.invoke(responseBody.movies)
                        } else {
                            onError.invoke()
                        }
                    } else {
                        onError.invoke()
                    }
                }

                override fun onFailure(call: Call<GetMovieResponses>, t: Throwable) {
                    onError.invoke()
                }
            })
    }

    fun getUpcomingMovies(
        page: Int = 1,
        onSuccess: (movies: List<Movie>) -> Unit,
        onError: KFunction1<Context, Unit>
    ) {
        api.getUpcomingMovies(page = page)
            .enqueue(object : Callback<GetMovieResponses> {
                override fun onResponse(
                    call: Call<GetMovieResponses>,
                    response: Response<GetMovieResponses>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()

                        if (responseBody != null) {
                            onSuccess.invoke(responseBody.movies)
                        } else {
                            onError.invoke()
                        }
                    } else {
                        onError.invoke()
                    }
                }

                override fun onFailure(call: Call<GetMovieResponses>, t: Throwable) {
                    onError.invoke()
                }
            })
    }

  

}

private fun <P1, R> (KFunction1<P1, R>).invoke() {

}

