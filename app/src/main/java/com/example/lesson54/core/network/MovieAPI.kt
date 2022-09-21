package com.example.lesson54.core.network

import com.example.lesson54.core.models.movieGenre.MovieGenreResponse
import com.example.lesson54.core.models.nowPlaying.NowPlayingMovieResponse
import com.example.lesson54.core.models.popular.PopularMovieResponse
import com.example.lesson54.core.models.topRated.TopRatedMovieResponse
import com.example.lesson54.core.models.upcoming.UpcomingMovieResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface MovieAPI {

    @GET("3/movie/popular?api_key=ae228a09fd0c71679dabcf913aea5d11&language=ru-RU")
    fun popularMovies(): Single<PopularMovieResponse>

    @GET("3/movie/top_rated?api_key=ae228a09fd0c71679dabcf913aea5d11&language=ru-RU")
    fun topRatedMovies(): Single<TopRatedMovieResponse>

    @GET("3/movie/now_playing?api_key=ae228a09fd0c71679dabcf913aea5d11&language=ru-RU")
    fun nowPlayingMovies(): Single<NowPlayingMovieResponse>

    @GET("3/movie/upcoming?api_key=ae228a09fd0c71679dabcf913aea5d11&language=ru-RU")
    fun upcomingMovies(): Single<UpcomingMovieResponse>

    @GET("3/genre/movie/list?api_key=ae228a09fd0c71679dabcf913aea5d11&language=ru-RU")
    fun genres(): Single<MovieGenreResponse>

}