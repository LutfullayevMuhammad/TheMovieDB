package com.example.lesson54.core.presenter

import com.example.lesson54.core.models.popular.PopularMovieResponse
import com.example.lesson54.core.models.popular.PopularResult
import com.example.lesson54.core.network.MovieAPIClient
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers

class SearchPresenter(
    private val view: HomePresenter.View,
    private val movieName: String,
    private val pageNum: String
) :
    HomePresenter.Presenter {

    private val compositeDisposable = CompositeDisposable()

    override fun loadData() {
        val call = MovieAPIClient.movieAPI()
        compositeDisposable.add(
            call.searchMovie(
                "ae228a09fd0c71679dabcf913aea5d11",
                movieName,
                pageNum
            ).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<PopularMovieResponse>() {
                    override fun onSuccess(t: PopularMovieResponse) {
                        view.showData(t.results as ArrayList<PopularResult>)
                    }

                    override fun onError(e: Throwable) {
                        view.showError(e.message!!)
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