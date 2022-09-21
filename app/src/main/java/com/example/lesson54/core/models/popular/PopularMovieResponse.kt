package com.example.lesson54.core.models.popular


import com.google.gson.annotations.SerializedName

data class PopularMovieResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<PopularResult>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)