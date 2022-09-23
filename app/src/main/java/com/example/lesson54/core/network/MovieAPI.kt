package com.example.lesson54.core.network

import com.example.lesson54.core.models.movieGenre.MovieGenreResponse
import com.example.lesson54.core.models.nowPlaying.NowPlayingMovieResponse
import com.example.lesson54.core.models.popular.PopularMovieResponse
import com.example.lesson54.core.models.topRated.TopRatedMovieResponse
import com.example.lesson54.core.models.upcoming.UpcomingMovieResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPI {

    @GET("3/movie/popular")
    fun popularMovies(
        @Query("page") page: String?,
        @Query("language") lang: String,
        @Query("api_key") apiKey: String
    ): Single<PopularMovieResponse>

    @GET("3/movie/top_rated")
    fun topRatedMovies(
        @Query("page") page: String?,
        @Query("language") lang: String,
        @Query("api_key") apiKey: String
    ): Single<TopRatedMovieResponse>

    @GET("3/movie/now_playing")
    fun nowPlayingMovies(
        @Query("page") page: String?,
        @Query("language") lang: String,
        @Query("api_key") apiKey: String
    ): Single<NowPlayingMovieResponse>

    @GET("3/movie/upcoming")
    fun upcomingMovies(
        @Query("page") page: String?,
        @Query("language") lang: String,
        @Query("api_key") apiKey: String
    ): Single<UpcomingMovieResponse>

    @GET("3/genre/movie/list?api_key=ae228a09fd0c71679dabcf913aea5d11&language=en-EN")
    fun genres(): Single<MovieGenreResponse>

}