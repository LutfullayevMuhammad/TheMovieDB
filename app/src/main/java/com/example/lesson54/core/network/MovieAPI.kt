package com.example.lesson54.core.network

import com.example.lesson54.core.models.movieGenre.MovieGenreResponse
import com.example.lesson54.core.models.movieType.MovieTypeResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieAPI {

    @GET("3/movie/{movie_type}")
    fun movieTypeDataCall(
        @Path("movie_type") movieType: String,
        @Query("page") page: String?,
        @Query("language") lang: String,
        @Query("api_key") apiKey: String
    ): Single<MovieTypeResponse>

    @GET("3/search/movie")
    fun searchMovie(
        @Query("api_key") apiKey: String,
        @Query("language") lang: String,
        @Query("query") movieType: String
    ): Single<MovieTypeResponse>

    @GET("3/genre/movie/list?api_key=ae228a09fd0c71679dabcf913aea5d11&language=en-EN")
    fun genres(): Single<MovieGenreResponse>

}