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
import com.example.lesson54.core.adapter.movieType1.MovieType1ListAdapter
import com.example.lesson54.core.adapter.movieType2.MovieType2ListAdapter
import com.example.lesson54.core.adapter.movieType2.ZoomOutPageTransformer
import com.example.lesson54.core.models.movieGenre.MovieGenreResponse
import com.example.lesson54.core.models.movieType.Result
import com.example.lesson54.core.presenter.HomePresenter
import com.example.lesson54.core.presenter.MainPresenterImp
import com.example.lesson54.databinding.FragmentHomeBinding
import com.example.lesson54.view.activity.MainActivity
import com.example.lesson54.view.base.BaseFragment
import java.util.*

class HomeFragment : BaseFragment(), HomePresenter.MainView {

    private lateinit var binding: FragmentHomeBinding
    private val popularListAdapter = MovieType2ListAdapter()
    private val topRatedListAdapter = MovieType1ListAdapter()
    private val nowPlayingListAdapter = MovieType1ListAdapter()
    private val upcomingListAdapter = MovieType1ListAdapter()
    private var presenter: HomePresenter.Presenter? = null
    private val handler = Handler()
    private var runnable: Runnable? = null
    private var scrollPosition = 0

    override fun getLayout(inflater: LayoutInflater, container: ViewGroup?): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
//        presenter?.loadGenres()
//        presenter?.loadData()
    }

    override fun onFragmentReady() {
        // preparing view-pager
        binding.popularMovies.adapter = popularListAdapter
        binding.popularMovies.apply {
            clipToPadding = false   // allow full width shown with padding
            clipChildren = false    // allow left/right item is not clipped
            offscreenPageLimit = 3  // make sure left/right item is rendered
        }
        binding.popularMovies.setPageTransformer(ZoomOutPageTransformer())
        // preparing lists
        binding.topRatedList.adapter = topRatedListAdapter
        binding.nowPlayingList.adapter = nowPlayingListAdapter
        binding.upcomingList.adapter = upcomingListAdapter
        binding.topRatedList.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.nowPlayingList.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.upcomingList.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        // loading data
        presenter = MainPresenterImp(this)
        presenter?.loadGenres()
        presenter?.loadData()
        // loading actions
        scrollPosition = binding.popularMovies.currentItem
        runnable = Runnable {
            if (scrollPosition == popularListAdapter.data.size) {
                binding.popularMovies.setCurrentItem(0, false)
                scrollPosition = 0
            } else {
                binding.popularMovies.setCurrentItem(scrollPosition++, true)
            }
        }

        binding.popularMovies.registerOnPageChangeCallback(pagerListener)


        // navigate to movieFragment
        popularListAdapter.onItemClick = {
            val action = HomeFragmentDirections.actionHomeFragmentToMovieFragment(it.id.toString())
            findNavController().navigate(action)
        }
        topRatedListAdapter.onItemClicked = {
            val action = HomeFragmentDirections.actionHomeFragmentToMovieFragment(it.id.toString())
            findNavController().navigate(action)
        }
        nowPlayingListAdapter.onItemClicked = {
            val action = HomeFragmentDirections.actionHomeFragmentToMovieFragment(it.id.toString())
            findNavController().navigate(action)
        }
        upcomingListAdapter.onItemClicked = {
            val action = HomeFragmentDirections.actionHomeFragmentToMovieFragment(it.id.toString())
            findNavController().navigate(action)
        }
        // navigate to searchFragment
        binding.searchHome.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToSearchFragment()
            findNavController().navigate(action)
        }
        // navigate to allFragment
        binding.popularAllBtn.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToAllFragment("popular")
            findNavController().navigate(action)
        }
        binding.topRatedAllBtn.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToAllFragment("top_rated")
            findNavController().navigate(action)
        }
        binding.nowPlayingAllBtn.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToAllFragment("now_playing")
            findNavController().navigate(action)
        }
        binding.upcomingAllBtn.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToAllFragment("upcoming")
            findNavController().navigate(action)
        }
    }

    override fun onFragmentCreated() {
        Timer().schedule(object : TimerTask() {
            // task to be scheduled
            override fun run() {
                runnable?.let { handler.post(it) }
            }
        }, 3000, 3000)
    }

    override fun onFragmentClosed() {
        presenter?.cancel()
        binding.popularMovies.unregisterOnPageChangeCallback(pagerListener)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.destroy()
    }

    override fun dataState(isLoading: Boolean) {
    }

    override fun getPopularMovieData(data: ArrayList<Result>) {
        popularListAdapter.data = data
    }

    override fun getTopRatedMovieData(data: ArrayList<Result>) {
        topRatedListAdapter.data = data
    }

    override fun getNowPlayingMovieData(data: ArrayList<Result>) {
        nowPlayingListAdapter.data = data
    }

    override fun getUpcomingMovieData(data: ArrayList<Result>) {
        upcomingListAdapter.data = data
    }

    override fun showError(message: String) {
        Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show()
    }

    override fun setGenres(g: MovieGenreResponse) {
        if (MainActivity.GENRES_DATA.size == 0) {
            MainActivity.GENRES_DATA.addAll(g.genres)
        }
    }

    val pagerListener=object :
        ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
//                binding.popularListBackground.load("https://image.tmdb.org/t/p/w500" + popularListAdapter.data[position].backdropPath)
            scrollPosition = binding.popularMovies.currentItem
        }

        override fun onPageScrollStateChanged(state: Int) {
            super.onPageScrollStateChanged(state)

            if (state == ViewPager2.SCROLL_STATE_IDLE) {
                when (binding.popularMovies.currentItem) {
                    popularListAdapter.data.size - 1 -> binding.popularMovies.setCurrentItem(
                        0,
                        false
                    )
                    0 -> binding.popularMovies.setCurrentItem(
                        popularListAdapter.data.size - 1,
                        false
                    )
                }
            }
        }
    }

}