package com.example.lesson54.core.presenter

import com.example.lesson54.core.models.movieGenre.MovieGenreResponse
import com.example.lesson54.core.models.movieType.Result

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
        fun getPopularMovieData(data: ArrayList<Result>)
        fun getTopRatedMovieData(data: ArrayList<Result>)
        fun getNowPlayingMovieData(data: ArrayList<Result>)
        fun getUpcomingMovieData(data: ArrayList<Result>)
        fun showError(message: String)
        fun setGenres(g: MovieGenreResponse)
    }
}