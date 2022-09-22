package com.example.lesson54.core.presenter

import com.example.lesson54.core.models.movieGenre.MovieGenreResponse
import com.example.lesson54.core.models.nowPlaying.NowPlayingResult
import com.example.lesson54.core.models.popular.PopularResult
import com.example.lesson54.core.models.topRated.TopRatedResult
import com.example.lesson54.core.models.upcoming.UpcomingResult

interface HomePresenter {
    interface Presenter {
        fun loadData()
        fun refreshData()
        fun loadGenres()
        fun cancel()
    }

    interface View {
        fun dataState(isLoading: Boolean)
        fun showData(popularData: ArrayList<PopularResult>)
        fun showTopRatedData(topRatedData:ArrayList<TopRatedResult>)
        fun showNowPlayingData(nowPlayingData:ArrayList<NowPlayingResult>)
        fun showUpcoming(upcomingData:ArrayList<UpcomingResult>)
        fun showError(message: String)
        fun setGenres(g: MovieGenreResponse)
    }
}