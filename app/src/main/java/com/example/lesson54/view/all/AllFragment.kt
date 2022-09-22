package com.example.lesson54.view.all

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lesson54.core.models.movieGenre.MovieGenreResponse
import com.example.lesson54.core.models.nowPlaying.NowPlayingResult
import com.example.lesson54.core.models.popular.PopularResult
import com.example.lesson54.core.models.topRated.TopRatedResult
import com.example.lesson54.core.models.upcoming.UpcomingResult
import com.example.lesson54.core.presenter.HomePresenter
import com.example.lesson54.core.presenter.PresenterImp
import com.example.lesson54.view.base.BaseFragment

class AllFragment:BaseFragment(),HomePresenter.View {
    override fun dataState(isLoading: Boolean) {
        TODO("Not yet implemented")
    }

    override fun showData(popularData: ArrayList<PopularResult>) {
        TODO("Not yet implemented")
    }

    override fun showTopRatedData(topRatedData: ArrayList<TopRatedResult>) {
        TODO("Not yet implemented")
    }

    override fun showNowPlayingData(nowPlayingData: ArrayList<NowPlayingResult>) {
        TODO("Not yet implemented")
    }

    override fun showUpcoming(upcomingData: ArrayList<UpcomingResult>) {
        TODO("Not yet implemented")
    }

    override fun showError(message: String) {
        TODO("Not yet implemented")
    }

    override fun setGenres(g: MovieGenreResponse) {
        TODO("Not yet implemented")
    }

    override fun getLayout(inflater: LayoutInflater, container: ViewGroup?): View {
        TODO("Not yet implemented")
    }

    override fun onFragmentReady() {
        TODO("Not yet implemented")
    }

    override fun onFragmentCreated() {
        TODO("Not yet implemented")
    }

    override fun onFragmentClosed() {
        TODO("Not yet implemented")
    }
}