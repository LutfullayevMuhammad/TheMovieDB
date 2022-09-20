package com.example.lesson54.core.presenter

import com.example.lesson54.core.models.topRated.Result
import com.example.lesson54.core.models.topRated.TopRatedMovieResponse
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
            .subscribe(object : SingleObserver<TopRatedMovieResponse> {
                override fun onSubscribe(d: Disposable) {
                    view.dataState(isLoading = true)
                }

                override fun onSuccess(t: TopRatedMovieResponse) {
                    view.showData(t.results as ArrayList<Result>)
                    view.dataState(isLoading = false)
                }

                override fun onError(e: Throwable) {
                    view.showError(e.message!!)
                    view.dataState(isLoading = false)
                }
            })
    }

    override fun refreshData() {

    }

}