package com.example.lesson54.view.search

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lesson54.core.adapter.search.SearchAdapter
import com.example.lesson54.core.models.movieGenre.MovieGenreResponse
import com.example.lesson54.core.models.nowPlaying.NowPlayingResult
import com.example.lesson54.core.models.popular.PopularResult
import com.example.lesson54.core.models.topRated.TopRatedResult
import com.example.lesson54.core.models.upcoming.UpcomingResult
import com.example.lesson54.core.presenter.HomePresenter
import com.example.lesson54.core.presenter.PresenterImp
import com.example.lesson54.core.presenter.SearchPresenter
import com.example.lesson54.databinding.FragmentSearchBinding
import com.example.lesson54.view.base.BaseFragment
import java.util.*
import kotlin.collections.ArrayList

class SearchFragment : BaseFragment(), HomePresenter.View {

    private lateinit var binding: FragmentSearchBinding
    private val adapter = SearchAdapter()
    private var presenter: HomePresenter.Presenter? = null
    private var pageNumber = 0

    override fun getLayout(inflater: LayoutInflater, container: ViewGroup?): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onFragmentReady() {
        // preparing list
        binding.searchingList.adapter = adapter
        binding.searchingList.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        // loading data
        binding.searchView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            var timer = Timer()
            val DELAY: Long = 1000
            override fun afterTextChanged(p0: Editable?) {
                timer.cancel()
                timer = Timer()
                timer.schedule(object : TimerTask() {
                    override fun run() {
                        presenter = SearchPresenter(
                            this@SearchFragment,
                            p0.toString(),
                            pageNumber.toString()
                        )
                        presenter?.loadData()
                    }
                }, DELAY)
            }
        })
    }

    override fun onFragmentCreated() {}

    override fun onFragmentClosed() {
        presenter?.cancel()
    }

    override fun dataState(isLoading: Boolean) {}

    override fun showData(popularData: ArrayList<PopularResult>) {
        adapter.data = popularData
    }

    override fun showTopRatedData(topRatedData: ArrayList<TopRatedResult>) {}

    override fun showNowPlayingData(nowPlayingData: ArrayList<NowPlayingResult>) {}

    override fun showUpcoming(upcomingData: ArrayList<UpcomingResult>) {}

    override fun showError(message: String) {}

    override fun setGenres(g: MovieGenreResponse) {}
}