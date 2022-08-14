package com.example.themoviedpapp.Models

import com.example.themoviedpapp.GetMovieResponses
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {



    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String = "b4b6c9b9560ed8c88109fb25999f02e0",
        @Query("page") page: Int
    ): Call<GetMovieResponses>

    @GET("movie/top_rated")
    fun getTopRatedMovies(
        @Query("api_key") apiKey: String = "b4b6c9b9560ed8c88109fb25999f02e0",
        @Query("page") page: Int
    ): Call<GetMovieResponses>

}