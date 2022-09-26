package com.example.lesson54.view.movie

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.example.lesson54.R
import com.example.lesson54.core.adapter.movie.actor.ActorMoviesAdapter
import com.example.lesson54.core.adapter.movie.similar.SimilarMoviesAdapter
import com.example.lesson54.core.adapter.movie.trailers.TrailersMoviesAdapter
import com.example.lesson54.core.models.movie.MovieGenre
import com.example.lesson54.core.models.movie.MovieResponse
import com.example.lesson54.core.models.movie.ProductionCountry
import com.example.lesson54.core.models.movieActors.Cast
import com.example.lesson54.core.models.movieGenre.MovieGenreResponse
import com.example.lesson54.core.models.movieTrailers.TrailersResult
import com.example.lesson54.core.models.similarMovies.SimilarResult
import com.example.lesson54.core.presenter.HomePresenter
import com.example.lesson54.core.presenter.MoviePresenterImp
import com.example.lesson54.databinding.FragmentMovieBinding
import com.example.lesson54.view.activity.MainActivity
import com.example.lesson54.view.base.BaseFragment

class MovieFragment : BaseFragment(), HomePresenter.MovieView {

    private lateinit var binding: FragmentMovieBinding
    private val actorMoviesAdapter = ActorMoviesAdapter()
    private val similarMoviesAdapter = SimilarMoviesAdapter()
    private val trailersMoviesAdapter = TrailersMoviesAdapter()
    private var presenter: HomePresenter.Presenter? = null
    private lateinit var movieResponse: MovieResponse
    private val args: MovieFragmentArgs by navArgs()

    override fun getLayout(inflater: LayoutInflater, container: ViewGroup?): View {
        binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onFragmentReady() {
        // preparing lists
        binding.actorsList.adapter = actorMoviesAdapter
        binding.actorsList.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.similarMoviesList.adapter = similarMoviesAdapter
        binding.similarMoviesList.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.trailersList.adapter = trailersMoviesAdapter
        binding.trailersList.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        // loading data
        presenter = MoviePresenterImp(this, args.movieId)
        presenter?.loadGenres()
        presenter?.loadData()

    }

    override fun onFragmentCreated() {

    }

    override fun onFragmentClosed() {
        presenter?.cancel()
    }

    override fun dataState(isLoading: Boolean) {

    }

    @SuppressLint("SetTextI18n")
    override fun getMovie(
        data: MovieResponse,
        productionCountry: ProductionCountry,
        movieGenre: MovieGenre
    ) {

        //Title
        binding.title.text = data.title

        //ImageLogo
        binding.logoImage.load("https://image.tmdb.org/t/p/w500" + data.posterPath)

        //Genre
        val date: String = buildString {
            for (i in 0 until 4) {
                append(data.releaseDate[i])
            }
        }
//        val movieGenres = ArrayList<String>()
//        MainActivity.GENRES_DATA.forEach {
//            if (movieGenre.id == it.id) {
//                movieGenres.add(it.name)
//            }
//        }
        if (binding.genre.text == "") {
            binding.genre.text = "$date • " + buildString {
                for (i in 0 until data.productionCountries.size - 1) {
                    append("${productionCountry.iso31661[i]}, ")
                }
                append(data.productionCountries[data.productionCountries.size - 1])
            } + buildString {
                for (i in 0 until data.genres.size - 1) {
                    append("${movieGenre.name[i]}, ")
                }
                append(data.genres[data.genres.size - 1])
            }
        }

        //Rating
        val rating: String = buildString {
            for (i in 0 until 3) {
                append(data.voteAverage.toString()[i])
            }
        }
        binding.rating.text = rating
        // vote text background
        if (rating < 7.0.toString()) {
            binding.rating.setBackgroundResource(R.drawable.rate_2_back)
        } else {
            binding.rating.setBackgroundResource(R.drawable.rate_1_back)
        }

        //OriginalLanguage
        binding.language.text = data.originalLanguage

        //Status
        binding.status.text = data.status

        //Revenue
        binding.revenue.text = "$${data.revenue}"

        //OriginalTitle
        binding.originalTitle.text = data.originalTitle

        //Overview
        binding.overview.text = data.tagline

        //Description
        binding.description.text = data.overview

    }

    override fun getMovieActor(data: ArrayList<Cast>) {
        actorMoviesAdapter.data = data
    }

    override fun getMovieTrailers(data: ArrayList<TrailersResult>) {
        trailersMoviesAdapter.data = data
    }

    override fun getSimilarMovies(data: ArrayList<SimilarResult>) {
        similarMoviesAdapter.data = data
    }

    override fun showError(message: String) {
        Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show()
    }

    override fun setGenres(g: MovieGenreResponse) {
        if (MainActivity.GENRES_DATA.size == 0) {
            MainActivity.GENRES_DATA.addAll(g.genres)
        }
    }

}