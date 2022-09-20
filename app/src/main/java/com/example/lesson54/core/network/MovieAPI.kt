package com.example.lesson54.core.network

import com.example.lesson54.core.models.movieGenre.Genre
import com.example.lesson54.core.models.movieGenre.MovieGenreResponse
import com.example.lesson54.core.models.topRated.TopRatedMovieResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieAPI {

    @GET("3/movie/popular?api_key=ae228a09fd0c71679dabcf913aea5d11&language=en-EN")
    fun popularMovies(): Single<TopRatedMovieResponse>

    @GET("3/genre/movie/list?api_key=ae228a09fd0c71679dabcf913aea5d11")
    fun genres(): Single<MovieGenreResponse>

}