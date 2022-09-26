package com.example.lesson54.core.models.similarMovies


import com.google.gson.annotations.SerializedName

data class MovieSimilarResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<SimilarResult>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)