package com.example.themoviedpapp.Models

import com.google.gson.annotations.SerializedName

class Movie {

    @SerializedName("id") val id: Long = 0
    @SerializedName("title") val title: String = ""
    @SerializedName("overview") val overview: String = ""
    @SerializedName("poster_path") val posterPath: String = ""
    @SerializedName("backdrop_path") val backdropPath: String = ""
    @SerializedName("vote_average") val rating: Float = 0.0f
    @SerializedName("release_date") val releaseDate: String = ""

}