package com.example.lesson54.view.movie

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.lesson54.core.adapter.allMovies.AllMoviesAdapter
import com.example.lesson54.core.models.movie.MovieResponse
import com.example.lesson54.core.models.movieActors.Cast
import com.example.lesson54.core.models.movieGenre.MovieGenreResponse
import com.example.lesson54.core.models.movieTrailers.TrailersResult
import com.example.lesson54.core.models.movieType.Result
import com.example.lesson54.core.models.similarMovies.SimilarResult
import com.example.lesson54.core.presenter.AllPresenterImp
import com.example.lesson54.core.presenter.HomePresenter
import com.example.lesson54.databinding.FragmentAllBinding
import com.example.lesson54.view.base.BaseFragment
import kotlin.collections.ArrayList

class MovieFragment : BaseFragment(),HomePresenter.MovieView{
    override fun getLayout(inflater: LayoutInflater, container: ViewGroup?): View {
        TODO("Not yet implemented")
    }

    override fun onFragmentReady() {
        TODO("Not yet implemented")
    }

    override fun onFragmentCreated() {
        TODO("Not yet implemented")
    }

    override fun onFragmentClosed() {
        TODO("Not yet implemented")
    }

    override fun dataState(isLoading: Boolean) {
        TODO("Not yet implemented")
    }

    override fun getMovie(data: ArrayList<MovieResponse>) {
        TODO("Not yet implemented")
    }

    override fun getMovieActor(data: ArrayList<Cast>) {
        TODO("Not yet implemented")
    }

    override fun getMovieTrailers(data: ArrayList<TrailersResult>) {
        TODO("Not yet implemented")
    }

    override fun getSimilarMovies(data: ArrayList<SimilarResult>) {
        TODO("Not yet implemented")
    }

    override fun showError(message: String) {
        TODO("Not yet implemented")
    }

    override fun setGenres(g: MovieGenreResponse) {
        TODO("Not yet implemented")
    }

}