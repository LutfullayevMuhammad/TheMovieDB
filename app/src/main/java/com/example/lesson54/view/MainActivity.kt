package com.example.lesson54.view

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import coil.load
import com.example.lesson54.core.adapter.nowPlaying.NowPlayingAdapter
import com.example.lesson54.core.adapter.popular.PopularMovieListAdapter
import com.example.lesson54.core.adapter.popular.ZoomOutPageTransformer
import com.example.lesson54.core.adapter.topRated.TopRatedAdapter
import com.example.lesson54.core.adapter.upcoming.UpcomingAdapter
import com.example.lesson54.core.models.movieGenre.Genre
import com.example.lesson54.core.models.movieGenre.MovieGenreResponse
import com.example.lesson54.core.models.nowPlaying.NowPlayingResult
import com.example.lesson54.core.models.popular.PopularResult
import com.example.lesson54.core.models.topRated.TopRatedMovieResponse
import com.example.lesson54.core.models.topRated.TopRatedResult
import com.example.lesson54.core.models.upcoming.UpcomingResult
import com.example.lesson54.core.network.MovieAPIClient
import com.example.lesson54.core.presenter.Presenter
import com.example.lesson54.core.presenter.PresenterImp
import com.example.lesson54.databinding.ActivityMainBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity(), Presenter.View {

    private lateinit var binding: ActivityMainBinding
    private val popularListAdapter = PopularMovieListAdapter()
    private val topRatedAdapter = TopRatedAdapter()
    private val nowPlayingAdapter = NowPlayingAdapter()
    private val upcomingAdapter = UpcomingAdapter()
    private var presenter: Presenter.Presenter? = null

    companion object {
        var GENRES_DATA = ArrayList<Genre>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.topRatedList.adapter = topRatedAdapter
        binding.topRatedList.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)

        binding.nowPlayingList.adapter = nowPlayingAdapter
        binding.nowPlayingList.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)

        binding.upcomingList.adapter = upcomingAdapter
        binding.upcomingList.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)

        // get genres
        MovieAPIClient.movieAPI().genres()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<MovieGenreResponse> {
                override fun onSubscribe(d: Disposable) {}

                override fun onSuccess(t: MovieGenreResponse) {
                    GENRES_DATA.addAll(t.genres)
                }

                override fun onError(e: Throwable) {}
            })
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
        presenter = PresenterImp(this)
        presenter?.loadData()
        // loading actions
        var scrollPosition = binding.trendMovies.currentItem
        val handler = Handler()
        val update = Runnable {
            if (scrollPosition == popularListAdapter.data.size) {
                scrollPosition = 0
            }
            //The second parameter ensures smooth scrolling
            binding.trendMovies.setCurrentItem(scrollPosition++, true)
        }

        Timer().schedule(object : TimerTask() {
            // task to be scheduled
            override fun run() {
                handler.post(update)
            }
        }, 2000, 1000)
        binding.trendMovies.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.popularListBackground.load("https://image.tmdb.org/t/p/w500" + popularListAdapter.data[position].backdropPath)
                scrollPosition = binding.trendMovies.currentItem
            }
        })
    }

    override fun dataState(isLoading: Boolean) {

    }

    override fun showData(data: ArrayList<PopularResult>) {
        popularListAdapter.data = data
        binding.popularListBackground.load("https://image.tmdb.org/t/p/w500" + data[0].backdropPath)
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
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}