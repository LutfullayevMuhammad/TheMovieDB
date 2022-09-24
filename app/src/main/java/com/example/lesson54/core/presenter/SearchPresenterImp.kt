package com.example.lesson54.core.presenter

import com.example.lesson54.core.models.movieType.MovieTypeResponse
import com.example.lesson54.core.models.movieType.Result
import com.example.lesson54.core.network.MovieAPIClient
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers

class SearchPresenterImp(
    private val view: HomePresenter.View,
    private val movieName: String,
    private val pageNum: String
) :
    HomePresenter.Presenter {

    private val compositeDisposable = CompositeDisposable()

    override fun loadData() {
        val call = MovieAPIClient.movieAPI()
        view.dataState(isLoading = true)
        compositeDisposable.add(
            call.searchMovie(
                "ae228a09fd0c71679dabcf913aea5d11",
                movieName,
                pageNum
            ).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<MovieTypeResponse>() {
                    override fun onSuccess(t: MovieTypeResponse) {
                        view.dataState(isLoading = false)
                        view.getData(data = t.results as ArrayList<Result>)
                    }

                    override fun onError(e: Throwable) {
                        view.dataState(isLoading = false)
                        view.showError(message = "On searching $movieName ${e.message} occurred")
                    }
                })
        )
    }

    override fun refreshData() {
        loadData()
    }

    override fun loadGenres() {}

    override fun cancel() {
        compositeDisposable.dispose()
    }
}