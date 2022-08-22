package com.example.themoviedpapp.Models

import com.example.themoviedpapp.GetMovieResponses
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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

    @GET("movie/upcoming")
    fun getUpcomingMovies(
        @Query("api_key") apiKey: String = "b4b6c9b9560ed8c88109fb25999f02e0",
        @Query("page") page: Int
    ): Call<GetMovieResponses>


    companion object {

        var retrofitService: Api? = null

        //Create the Retrofit service instance using the retrofit.
    fun getInstance(): Api {

        if (retrofitService == null) {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://fake-movie-database-api.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            retrofitService = retrofit.create(Api::class.java)
        }
        return retrofitService!!
    }
}
}