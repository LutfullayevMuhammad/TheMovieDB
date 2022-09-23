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

    var page = 1

    override fun dataState(isLoading: Boolean) {

    }

    override fun showData(popularData: ArrayList<PopularResult>) {

        popularAllListAdapter.data = popularData
    }

    override fun showTopRatedData(topRatedData: ArrayList<TopRatedResult>) {

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
        binding = FragmentAllPopularBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onFragmentReady() {
        binding.allList.adapter = popularAllListAdapter
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