package com.example.lesson54.core.presenter

import com.example.lesson54.core.models.movieType.MovieTypeResponse
import com.example.lesson54.core.models.movieType.Result
import com.example.lesson54.core.network.MovieAPIClient
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*
import kotlin.collections.ArrayList

class SearchPresenterImp(private val view: HomePresenter.View, ) :
    HomePresenter.Presenter {

    private val compositeDisposable = CompositeDisposable()

    private var pageNum: Int=1


    private var searchText=""

    val call = MovieAPIClient.movieAPI()

    override fun loadData() {

    }


    var timer = Timer()
    val DELAY: Long = 1000

    override fun searchData(text: String) {
        timer.cancel()
        if (text.isNotBlank()) {
            timer = Timer()
            timer.schedule(object : TimerTask() {
                override fun run() {
                    delayedSearch(text)
                }
            }, DELAY)
        }
    }

    override fun loadNextData(text: String) {
        view.dataState(isLoading = true)
        compositeDisposable.add(
            call.searchMovie(
                apiKey = "ae228a09fd0c71679dabcf913aea5d11",
                lang = "en-EN",
                movieType = searchText,
                page = pageNum
            ).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<MovieTypeResponse>() {
                    override fun onSuccess(t: MovieTypeResponse) {
                        view.dataState(isLoading = false)
                        view.setNextData(data = t.results as ArrayList<Result>)
                        pageNum++
                    }

                    override fun onError(e: Throwable) {
                        view.dataState(isLoading = false)
                        view.showError(message = "On searching $searchText ${e.message} occurred")
                    }
                })
        )
    }

    private fun delayedSearch(text: String) {
        searchText=text
        view.dataState(isLoading = true)
        compositeDisposable.add(
            call.searchMovie(
                apiKey = "ae228a09fd0c71679dabcf913aea5d11",
                lang = "en-EN",
                movieType = text,
                page = pageNum
            ).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<MovieTypeResponse>() {
                    override fun onSuccess(t: MovieTypeResponse) {
                        view.dataState(isLoading = false)
                        view.getData(data = t.results as ArrayList<Result>)
                        pageNum++
                    }

                    override fun onError(e: Throwable) {
                        view.dataState(isLoading = false)
                        view.showError(message = "On searching $text ${e.message} occurred")
                    }
                })
        )
    }

    override fun refreshData() {
        loadData()
    }

    override fun loadGenres() {}

    override fun cancel() {
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }

    override fun destroy() {
        compositeDisposable.clear()
    }
}