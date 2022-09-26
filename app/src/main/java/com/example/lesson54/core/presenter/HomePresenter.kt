package com.example.lesson54.core.presenter

import com.example.lesson54.core.models.movie.MovieResponse
import com.example.lesson54.core.models.movieActors.Cast
import com.example.lesson54.core.models.movieGenre.MovieGenreResponse
import com.example.lesson54.core.models.movieTrailers.TrailersResult
import com.example.lesson54.core.models.movieType.Result
import com.example.lesson54.core.models.similarMovies.SimilarResult

interface HomePresenter {
    interface Presenter {
        fun loadData()
        fun refreshData()
        fun loadGenres()
        fun cancel()
    }

    interface View {
        fun dataState(isLoading: Boolean)
        fun getData(data: ArrayList<Result>)
        fun showError(message: String)
    }

    interface MainView {
        fun dataState(isLoading: Boolean)
        fun getPopularMovieData(data: ArrayList<Result>)
        fun getTopRatedMovieData(data: ArrayList<Result>)
        fun getNowPlayingMovieData(data: ArrayList<Result>)
        fun getUpcomingMovieData(data: ArrayList<Result>)
        fun showError(message: String)
        fun setGenres(g: MovieGenreResponse)
    }

    interface MovieView{
        fun dataState(isLoading: Boolean)
        fun getMovie(data:ArrayList<MovieResponse>)
        fun getMovieActor(data:ArrayList<Cast>)
        fun getMovieTrailers(data:ArrayList<TrailersResult>)
        fun getSimilarMovies(data:ArrayList<SimilarResult>)
        fun showError(message: String)
        fun setGenres(g: MovieGenreResponse)
    }

}