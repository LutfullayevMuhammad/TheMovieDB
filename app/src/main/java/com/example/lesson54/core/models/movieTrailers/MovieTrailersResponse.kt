package com.example.lesson54.core.models.movieTrailers


import com.google.gson.annotations.SerializedName

data class MovieTrailersResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("results")
    val results: List<TrailersResult>
)