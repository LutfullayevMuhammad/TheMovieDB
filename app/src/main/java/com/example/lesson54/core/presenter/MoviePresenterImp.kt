package com.example.lesson54.core.presenter

import com.example.lesson54.core.models.movie.MovieResponse
import com.example.lesson54.core.models.movieActors.Cast
import com.example.lesson54.core.models.movieActors.MovieActorsResponse
import com.example.lesson54.core.models.movieTrailers.MovieTrailersResponse
import com.example.lesson54.core.models.movieTrailers.TrailersResult
import com.example.lesson54.core.models.movieType.MovieTypeResponse
import com.example.lesson54.core.models.movieType.Result
import com.example.lesson54.core.network.MovieAPIClient
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers

class MoviePresenterImp(
    private val view: HomePresenter.MovieView,
    private val movieId: String
) : HomePresenter.Presenter {

    private val compositeDisposable = CompositeDisposable()
    override fun loadData() {
        movieRequest()
        similarMovieRequest()
        trailersMovieRequest()
        actorMovieRequest()
    }

    private fun movieRequest() {

        val call = MovieAPIClient.movieAPI()
        compositeDisposable.add(
            call.movie(
                movieId = movieId,
                lang = "en-EN",
                apiKey = "ae228a09fd0c71679dabcf913aea5d11"
            )
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(object :
                    DisposableSingleObserver<MovieResponse>() {
                    override fun onSuccess(t: MovieResponse) {
                        view.dataState(false)
                        view.getMovie(t)
                    }

                    override fun onError(e: Throwable) {
                        view.dataState(false)
                        view.showError(message = "On loading Movies ${e.message} occurred")
                    }
                })
        )
    }

    private fun actorMovieRequest() {
        val call = MovieAPIClient.movieAPI()
        compositeDisposable.add(
            call.movieActor(
                movieId = movieId,
                apiKey = "ae228a09fd0c71679dabcf913aea5d11",
                lang = "en-EN"
            )
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(object :
                    DisposableSingleObserver<MovieActorsResponse>() {
                    override fun onSuccess(t: MovieActorsResponse) {
                        view.dataState(false)
                        view.getMovieActor(t.cast as ArrayList<Cast>)
                    }

                    override fun onError(e: Throwable) {
                        view.dataState(false)
                        view.showError(message = "On loading Movie Actors ${e.message} occurred")
                    }
                })
        )
    }

    private fun trailersMovieRequest() {
        val call = MovieAPIClient.movieAPI()
        compositeDisposable.add(
            call.movieTrailers(
                movieId = movieId,
                apiKey = "ae228a09fd0c71679dabcf913aea5d11",
                lang = "en-EN",

                )
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(object :
                    DisposableSingleObserver<MovieTrailersResponse>() {
                    override fun onSuccess(t: MovieTrailersResponse) {
                        view.dataState(false)
                        view.getMovieTrailers(t.results as ArrayList<TrailersResult>)
                    }

                    override fun onError(e: Throwable) {
                        view.dataState(false)
                        view.showError(message = "On loading Movie Trailers ${e.message} occurred")
                    }
                })
        )
    }

    private fun similarMovieRequest() {
        val call = MovieAPIClient.movieAPI()
        compositeDisposable.add(
            call.movieSimilar(
                movieId = movieId,
                apiKey = "ae228a09fd0c71679dabcf913aea5d11",
                lang = "en-EN",
            )
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(object :
                    DisposableSingleObserver<MovieTypeResponse>() {
                    override fun onSuccess(t: MovieTypeResponse) {
                        view.dataState(false)
                        view.getSimilarMovies(t.results as ArrayList<Result>)
                    }

                    override fun onError(e: Throwable) {
                        view.showError(message = "On loading Movie Similar ${e.message} occurred")
                    }
                })
        )
    }

    override fun refreshData() {
        loadData()
        loadGenres()
    }

    override fun loadGenres() {}

    override fun cancel() {
        compositeDisposable.dispose()
    }

}