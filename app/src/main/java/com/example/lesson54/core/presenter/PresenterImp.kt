package com.example.lesson54.core.presenter

import com.example.lesson54.core.models.nowPlaying.NowPlayingMovieResponse
import com.example.lesson54.core.models.nowPlaying.NowPlayingResult
import com.example.lesson54.core.models.popular.PopularMovieResponse
import com.example.lesson54.core.models.popular.PopularResult
import com.example.lesson54.core.models.topRated.TopRatedMovieResponse
import com.example.lesson54.core.models.topRated.TopRatedResult
import com.example.lesson54.core.models.upcoming.UpcomingMovieResponse
import com.example.lesson54.core.models.upcoming.UpcomingResult
import com.example.lesson54.core.network.MovieAPIClient
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class PresenterImp(private val view: Presenter.View) : Presenter.Presenter {

    override fun loadData() {

        val call = MovieAPIClient.movieAPI()

        call.popularMovies()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<PopularMovieResponse> {
                override fun onSubscribe(d: Disposable) {
                    view.dataState(true)
                }

                override fun onSuccess(t: PopularMovieResponse) {
                    view.showData(t.results as ArrayList<PopularResult>)
                    view.dataState(isLoading = false)
                }

                override fun onError(e: Throwable) {
                    view.showError(e.message!!)
                    view.dataState(isLoading = false)
                }

            })

        call.topRatedMovies()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object :SingleObserver<TopRatedMovieResponse>{
                override fun onSubscribe(d: Disposable) {
                    view.dataState(true)
                }

                override fun onSuccess(t: TopRatedMovieResponse) {
                    view.showTopRatedData(t.results as ArrayList<TopRatedResult>)
                }

                override fun onError(e: Throwable) {
                    view.showError(e.message!!)
                    view.dataState(false)
                }

            })
        call.nowPlayingMovies()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object :SingleObserver<NowPlayingMovieResponse>{
                override fun onSubscribe(d: Disposable) {
                    view.dataState(true)
                }

                override fun onSuccess(t: NowPlayingMovieResponse) {
                    view.showNowPlayingData(t.results as ArrayList<NowPlayingResult>)
                }

                override fun onError(e: Throwable) {
                    view.showError(e.message!!)
                    view.dataState(false)
                }

            })
        call.upcomingMovies()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object :SingleObserver<UpcomingMovieResponse>{
                override fun onSubscribe(d: Disposable) {
                    view.dataState(true)
                }

                override fun onSuccess(t: UpcomingMovieResponse) {
                    view.showUpcoming(t.results as ArrayList<UpcomingResult>)
                }

                override fun onError(e: Throwable) {
                    view.showError(e.message!!)
                    view.dataState(false)
                }

            })
    }

    override fun refreshData() {

    }

}