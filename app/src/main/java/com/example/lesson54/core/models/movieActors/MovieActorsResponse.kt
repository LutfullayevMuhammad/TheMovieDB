package com.example.lesson54.core.models.movieActors


import com.google.gson.annotations.SerializedName

data class MovieActorsResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("cast")
    val cast: List<Cast>,
    @SerializedName("crew")
    val crew: List<Crew>
)