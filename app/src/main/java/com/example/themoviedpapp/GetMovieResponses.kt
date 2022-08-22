package com.example.themoviedpapp

import com.example.themoviedpapp.Models.Movie
import com.google.gson.annotations.SerializedName

data class GetMovieResponses (

    @SerializedName("page") val page: Int = 0,
    @SerializedName("results") val movies: List<Movie>,
    @SerializedName("total_pages") val pages: Int = 0



    )