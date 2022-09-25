package com.example.lesson54.core.presenter

import com.example.lesson54.core.models.movieType.MovieTypeResponse
import com.example.lesson54.core.models.movieType.Result
import com.example.lesson54.core.network.MovieAPIClient
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers

class AllPresenterImp(
    private val view: HomePresenter.View,
    private val movieType: String,
    private val page: String
) : HomePresenter.Presenter {

    private val compositeDisposable = CompositeDisposable()

    override fun loadData() {
        view.dataState(isLoading = false)

        val call = MovieAPIClient.movieAPI()
        compositeDisposable.add(
            call.movieTypeDataCall(
                movieType = movieType,
                page = page,
                lang = "en-EN",
                apiKey = "ae228a09fd0c71679dabcf913aea5d11"
            )
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<MovieTypeResponse>() {
                    override fun onSuccess(t: MovieTypeResponse) {
                        view.dataState(isLoading = false)
                        view.getData(t.results as ArrayList<Result>)
                    }

                    override fun onError(e: Throwable) {
                        e.message?.let { view.showError(message = it) }
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