package com.example.lesson54.core.models.movieGenre


import com.google.gson.annotations.SerializedName

data class MovieGenreResponse(
    @SerializedName("genres")
    val genres: List<Genre>
)