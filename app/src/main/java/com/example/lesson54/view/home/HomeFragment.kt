package com.example.lesson54.view.home

import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import coil.load
import com.example.lesson54.core.adapter.nowPlaying.NowPlayingAdapter
import com.example.lesson54.core.adapter.popular.PopularMovieListAdapter
import com.example.lesson54.core.adapter.popular.ZoomOutPageTransformer
import com.example.lesson54.core.adapter.topRated.TopRatedAdapter
import com.example.lesson54.core.adapter.upcoming.UpcomingAdapter
import com.example.lesson54.core.models.movieGenre.MovieGenreResponse
import com.example.lesson54.core.models.nowPlaying.NowPlayingResult
import com.example.lesson54.core.models.popular.PopularResult
import com.example.lesson54.core.models.topRated.TopRatedResult
import com.example.lesson54.core.models.upcoming.UpcomingResult
import com.example.lesson54.core.presenter.HomePresenter
import com.example.lesson54.core.presenter.PresenterImp
import com.example.lesson54.databinding.FragmentHomeBinding
import com.example.lesson54.view.MainActivity
import com.example.lesson54.view.base.BaseFragment
import java.util.*
import kotlin.collections.ArrayList

class HomeFragment : BaseFragment(), HomePresenter.View {

    private lateinit var binding: FragmentHomeBinding
    private val popularListAdapter = PopularMovieListAdapter()
    private val topRatedAdapter = TopRatedAdapter()
    private val nowPlayingAdapter = NowPlayingAdapter()
    private val upcomingAdapter = UpcomingAdapter()
    private var presenter: HomePresenter.Presenter? = null
    private val handler = Handler()
    private var runnable: Runnable? = null
    private var scrollPosition = 0

    override fun getLayout(inflater: LayoutInflater, container: ViewGroup?): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onFragmentReady() {
        binding.topRatedList.adapter = topRatedAdapter
        binding.topRatedList.layoutManager = LinearLayoutManager(
            requireActivity(),
            LinearLayoutManager.HORIZONTAL, false
        )
        binding.nowPlayingList.adapter = nowPlayingAdapter
        binding.nowPlayingList.layoutManager = LinearLayoutManager(
            requireActivity(),
            LinearLayoutManager.HORIZONTAL, false
        )
        binding.upcomingList.adapter = upcomingAdapter
        binding.upcomingList.layoutManager = LinearLayoutManager(
            requireActivity(),
            LinearLayoutManager.HORIZONTAL, false
        )
        // preparing view-pager
        binding.trendMovies.adapter = popularListAdapter
        binding.trendMovies.apply {
            clipToPadding = false   // allow full width shown with padding
            clipChildren = false    // allow left/right item is not clipped
            offscreenPageLimit = 3  // make sure left/right item is rendered
        }
        binding.trendMovies.setPageTransformer(ZoomOutPageTransformer())
        binding.trendMovies.overScrollMode = View.OVER_SCROLL_NEVER
        // loading data
        presenter = PresenterImp(this, null)
        presenter?.loadGenres()
        presenter?.loadData()
        // loading actions
        scrollPosition = binding.trendMovies.currentItem
        runnable = Runnable {
            if (scrollPosition == popularListAdapter.data.size) {
                scrollPosition = 0
            }
            //The second parameter ensures smooth scrolling
            binding.trendMovies.setCurrentItem(scrollPosition++, true)
        }
        binding.trendMovies.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.popularListBackground.load("https://image.tmdb.org/t/p/w500" + popularListAdapter.data[position].backdropPath)
                scrollPosition = binding.trendMovies.currentItem
            }
        })
        binding.searchHome.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToSearchFragment()
            findNavController().navigate(action)
        }
        binding.popularAllBtn.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToAllFragment()
            findNavController().navigate(action)
        }
    }

    override fun onFragmentCreated() {
        Timer().schedule(object : TimerTask() {
            // task to be scheduled
            override fun run() {
                runnable?.let { handler.post(it) }
            }
        }, 2000, 1000)
    }

    override fun onFragmentClosed() {
        presenter?.cancel()
    }

    override fun dataState(isLoading: Boolean) {}

    override fun showData(popularData: ArrayList<PopularResult>) {
        popularListAdapter.data = popularData
        binding.popularListBackground.load("https://image.tmdb.org/t/p/w500" + popularData[0].backdropPath)
    }

    override fun showTopRatedData(topRatedData: ArrayList<TopRatedResult>) {
        topRatedAdapter.data = topRatedData
    }

    override fun showNowPlayingData(nowPlayingData: ArrayList<NowPlayingResult>) {
        nowPlayingAdapter.data = nowPlayingData
    }

    override fun showUpcoming(upcomingData: ArrayList<UpcomingResult>) {
        upcomingAdapter.data = upcomingData
    }

    override fun showError(message: String) {
        Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show()
    }

    override fun setGenres(g: MovieGenreResponse) {
        MainActivity.GENRES_DATA.addAll(g.genres)
    }


}