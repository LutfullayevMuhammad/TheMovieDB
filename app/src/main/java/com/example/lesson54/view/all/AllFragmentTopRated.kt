package com.example.lesson54.view.all

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.lesson54.core.adapter.all.popular.PopularAllAdapter
import com.example.lesson54.core.adapter.all.topRated.TopRatedAllAdapter
import com.example.lesson54.core.models.movieGenre.MovieGenreResponse
import com.example.lesson54.core.models.nowPlaying.NowPlayingResult
import com.example.lesson54.core.models.popular.PopularResult
import com.example.lesson54.core.models.topRated.TopRatedResult
import com.example.lesson54.core.models.upcoming.UpcomingResult
import com.example.lesson54.core.presenter.HomePresenter
import com.example.lesson54.core.presenter.PresenterImp
import com.example.lesson54.databinding.FragmentAllPopularBinding
import com.example.lesson54.databinding.FragmentAllTopRatedBinding
import com.example.lesson54.view.MainActivity
import com.example.lesson54.view.base.BaseFragment

class AllFragmentTopRated:BaseFragment(),HomePresenter.View {

    private lateinit var binding: FragmentAllTopRatedBinding

    private val topRatedAllListAdapter = TopRatedAllAdapter()
    private var presenter: HomePresenter.Presenter? = null

    var page = 1

    override fun dataState(isLoading: Boolean) {

    }

    override fun showData(popularData: ArrayList<PopularResult>) {

    }

    override fun showTopRatedData(topRatedData: ArrayList<TopRatedResult>) {
        topRatedAllListAdapter.data = topRatedData
    }

    override fun showNowPlayingData(nowPlayingData: ArrayList<NowPlayingResult>) {

    }

    override fun showUpcoming(upcomingData: ArrayList<UpcomingResult>) {

    }

    override fun showError(message: String) {
        Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show()
    }

    override fun setGenres(g: MovieGenreResponse) {
        MainActivity.GENRES_DATA.addAll(g.genres)
    }

    override fun getLayout(inflater: LayoutInflater, container: ViewGroup?): View {
        binding = FragmentAllTopRatedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onFragmentReady() {
        binding.allList.adapter = topRatedAllListAdapter
        binding.allList.layoutManager = GridLayoutManager(
            requireActivity(), 2
        )

        presenter = PresenterImp(this)

        presenter?.loadGenres()
        presenter?.loadData()
    }

    override fun onFragmentCreated() {

    }

    override fun onFragmentClosed() {
        presenter?.cancel()
    }
}