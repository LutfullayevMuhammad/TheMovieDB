package com.example.lesson54.core.presenter

import com.example.lesson54.core.models.movieGenre.MovieGenreResponse
import com.example.lesson54.core.models.movieType.MovieTypeResponse
import com.example.lesson54.core.models.movieType.Result
import com.example.lesson54.core.network.MovieAPIClient
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers

class MainPresenterImp(
    private val view: HomePresenter.MainView
) :
    HomePresenter.Presenter {

    private val compositeDisposable = CompositeDisposable()

    override fun loadData() {
        popularMovieRequest()
        topRatedMovieRequest()
        nowPlayingMovieRequest()
        upcomingMovieRequest()
    }

    private fun popularMovieRequest() {
        val call = MovieAPIClient.movieAPI()
        compositeDisposable.add(
            call.movieTypeDataCall(
                movieType = "popular",
                page = "1",
                lang = "en-EN",
                apiKey = "ae228a09fd0c71679dabcf913aea5d11"
            )
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(object :
                    DisposableSingleObserver<MovieTypeResponse>() {
                    override fun onSuccess(t: MovieTypeResponse) {
                        view.getPopularMovieData(data = t.results as ArrayList<Result>)
                    }

                    override fun onError(e: Throwable) {
                        view.showError(message = "On loading Popular Movies ${e.message} occurred")
                    }
                })
        )
    }

    private fun topRatedMovieRequest() {
        val call = MovieAPIClient.movieAPI()
        compositeDisposable.add(
            call.movieTypeDataCall(
                movieType = "top_rated",
                page = "1",
                lang = "en-EN",
                apiKey = "ae228a09fd0c71679dabcf913aea5d11"
            )
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(object :
                    DisposableSingleObserver<MovieTypeResponse>() {
                    override fun onSuccess(t: MovieTypeResponse) {
                        view.getTopRatedMovieData(data = t.results as ArrayList<Result>)
                    }

                    override fun onError(e: Throwable) {
                        view.showError(message = "On loading Popular Movies ${e.message} occurred")
                    }
                })
        )
    }

    private fun nowPlayingMovieRequest() {
        val call = MovieAPIClient.movieAPI()
        compositeDisposable.add(
            call.movieTypeDataCall(
                movieType = "now_playing",
                page = "1",
                lang = "en-EN",
                apiKey = "ae228a09fd0c71679dabcf913aea5d11"
            )
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(object :
                    DisposableSingleObserver<MovieTypeResponse>() {
                    override fun onSuccess(t: MovieTypeResponse) {
                        view.getNowPlayingMovieData(data = t.results as ArrayList<Result>)
                    }

                    override fun onError(e: Throwable) {
                        view.showError(message = "On loading Popular Movies ${e.message} occurred")
                    }
                })
        )
    }

    private fun upcomingMovieRequest() {
        val call = MovieAPIClient.movieAPI()
        compositeDisposable.add(
            call.movieTypeDataCall(
                movieType = "upcoming",
                page = "1",
                lang = "en-EN",
                apiKey = "ae228a09fd0c71679dabcf913aea5d11"
            )
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(object :
                    DisposableSingleObserver<MovieTypeResponse>() {
                    override fun onSuccess(t: MovieTypeResponse) {
                        view.getUpcomingMovieData(data = t.results as ArrayList<Result>)
                    }

                    override fun onError(e: Throwable) {
                        view.showError(message = "On loading Popular Movies ${e.message} occurred")
                    }
                })
        )
    }

    override fun refreshData() {
        loadGenres()
        loadData()
    }

    override fun loadGenres() {
        MovieAPIClient.movieAPI().genres()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<MovieGenreResponse> {
                override fun onSubscribe(d: Disposable) {}

                override fun onSuccess(t: MovieGenreResponse) {
                    view.setGenres(t)
                }

                override fun onError(e: Throwable) {
                    view.showError("On loading genres ${e.message} occurred")
                }
            })
    }

    override fun cancel() {
        compositeDisposable.dispose()
    }

}