package com.example.themoviedpapp

import com.google.gson.annotations.SerializedName

data class GetMovieResponses (

    @SerializedName("page") val page: Int = 0,
    @SerializedName("results") val movies: List<Movie> = TODO(),
    @SerializedName("total_pages") val pages: Int = 0



    )