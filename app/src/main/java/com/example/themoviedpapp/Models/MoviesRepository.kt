package com.example.themoviedpapp.Models

import com.example.themoviedpapp.GetMovieResponses
import com.example.themoviedpapp.Movie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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
    fun getPopularMovies(page: Int = 1,
                         onSuccess: (movies: List<Movie>) -> Unit,
                         onError: () -> Unit
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
        onError: () -> Unit
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
}
