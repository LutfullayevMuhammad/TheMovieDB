package com.example.lesson54.core.models.nowPlaying


import com.google.gson.annotations.SerializedName

data class NowPlayingMovieResponse(
    @SerializedName("dates")
    val dates: Dates,
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<NowPlayingResult>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)