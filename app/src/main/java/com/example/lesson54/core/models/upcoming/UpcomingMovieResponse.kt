package com.example.lesson54.core.models.upcoming


import com.google.gson.annotations.SerializedName

data class UpcomingMovieResponse(
    @SerializedName("dates")
    val dates: Dates,
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<UpcomingResult>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)