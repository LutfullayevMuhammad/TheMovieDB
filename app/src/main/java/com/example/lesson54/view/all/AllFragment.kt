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
import com.example.lesson54.databinding.FragmentAllBinding
import com.example.lesson54.view.base.BaseFragment

class AllFragment : BaseFragment(), HomePresenter.View {

    private lateinit var binding: FragmentAllBinding
    private var presenter: HomePresenter.Presenter? = null

    override fun getLayout(inflater: LayoutInflater, container: ViewGroup?): View {
        binding = FragmentAllBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onFragmentReady() {

    }

    override fun onFragmentCreated() {

    }

    override fun onFragmentClosed() {

    }

    override fun dataState(isLoading: Boolean) {

    }

    override fun showData(popularData: ArrayList<PopularResult>) {

    }

    override fun showTopRatedData(topRatedData: ArrayList<TopRatedResult>) {

    }

    override fun showNowPlayingData(nowPlayingData: ArrayList<NowPlayingResult>) {

    }

    override fun showUpcoming(upcomingData: ArrayList<UpcomingResult>) {

    }

    override fun showError(message: String) {

    }

    override fun setGenres(g: MovieGenreResponse) {

    }
}