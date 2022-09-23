package com.example.lesson54.core.presenter

import com.example.lesson54.core.models.movieGenre.MovieGenreResponse
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
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers

class PresenterImp(private val view: HomePresenter.View, private val page: String?) :
    HomePresenter.Presenter {

    private val compositeDisposable = CompositeDisposable()

    override fun loadData() {
        val call = MovieAPIClient.movieAPI()
        view.dataState(true)
        compositeDisposable.add(
            call.popularMovies(page, "en-EN", "ae228a09fd0c71679dabcf913aea5d11")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(object :
                    DisposableSingleObserver<PopularMovieResponse>() {

                    override fun onSuccess(t: PopularMovieResponse) {
                        view.showData(t.results as ArrayList<PopularResult>)
                        view.dataState(isLoading = false)
                    }

                    override fun onError(e: Throwable) {
                        view.showError(e.message!!)
                        view.dataState(isLoading = false)
                    }
                })
        )
        view.dataState(true)
        compositeDisposable.add(
            call.topRatedMovies(page, "en-EN", "ae228a09fd0c71679dabcf913aea5d11")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<TopRatedMovieResponse>() {
                    override fun onSuccess(t: TopRatedMovieResponse) {
                        view.showTopRatedData(t.results as ArrayList<TopRatedResult>)
                    }

                    override fun onError(e: Throwable) {
                        view.showError(e.message!!)
                        view.dataState(false)
                    }
                })
        )
        view.dataState(true)
        compositeDisposable.add(
            call.nowPlayingMovies(page, "en-EN", "ae228a09fd0c71679dabcf913aea5d11")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<NowPlayingMovieResponse>() {
                    override fun onSuccess(t: NowPlayingMovieResponse) {
                        view.showNowPlayingData(t.results as ArrayList<NowPlayingResult>)
                    }

                    override fun onError(e: Throwable) {
                        view.showError(e.message!!)
                        view.dataState(false)
                    }
                })
        )
        view.dataState(true)
        compositeDisposable.add(
            call.upcomingMovies(page, "en-EN", "ae228a09fd0c71679dabcf913aea5d11")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<UpcomingMovieResponse>() {
                    override fun onSuccess(t: UpcomingMovieResponse) {
                        view.showUpcoming(t.results as ArrayList<UpcomingResult>)
                    }

                    override fun onError(e: Throwable) {
                        view.showError(e.message!!)
                        view.dataState(false)
                    }
                })
        )
    }

    override fun refreshData() {
        loadGenres()
        loadData()
    }

    override fun loadGenres() {
        // get genres
        MovieAPIClient.movieAPI().genres()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
//            .delay(10000,TimeUnit.MILLISECONDS)
            .subscribe(object : SingleObserver<MovieGenreResponse> {
                override fun onSubscribe(d: Disposable) {}

                override fun onSuccess(t: MovieGenreResponse) {
                    view.setGenres(t)
                }

                override fun onError(e: Throwable) {}
            })
    }

    override fun cancel() {
        compositeDisposable.dispose()
    }

}