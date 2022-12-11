package com.example.lesson54.view.movie

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.example.lesson54.R
import com.example.lesson54.core.adapter.movie.actor.ActorMoviesAdapter
import com.example.lesson54.core.adapter.movie.similar.SimilarMoviesAdapter
import com.example.lesson54.core.adapter.movie.trailers.TrailersMoviesAdapter
import com.example.lesson54.core.models.movie.MovieResponse
import com.example.lesson54.core.models.movieActors.Cast
import com.example.lesson54.core.models.movieTrailers.TrailersResult
import com.example.lesson54.core.models.movieType.Result
import com.example.lesson54.core.presenter.HomePresenter
import com.example.lesson54.core.presenter.MoviePresenterImp
import com.example.lesson54.databinding.FragmentMovieBinding
import com.example.lesson54.view.base.BaseFragment
import com.example.lesson54.view.home.HomeFragmentDirections

class MovieFragment : BaseFragment(), HomePresenter.MovieView {

    private lateinit var binding: FragmentMovieBinding
    private val actorMoviesAdapter = ActorMoviesAdapter()
    private val similarMoviesAdapter = SimilarMoviesAdapter()
    private val trailersMoviesAdapter = TrailersMoviesAdapter()
    private var presenter: HomePresenter.Presenter? = null
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
        presenter?.loadData()

        binding.back.setOnClickListener {
            requireActivity().onBackPressed()
        }

    }

    override fun onFragmentCreated() {

    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.destroy()
    }

    override fun onFragmentClosed() {
        presenter?.cancel()
    }

    override fun dataState(isLoading: Boolean) {

    }

    @SuppressLint("SetTextI18n")
    override fun getMovie(data: MovieResponse) {
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

        if (binding.genre.text == "") {
            binding.genre.text = "$date • " + buildString {
                for (i in 0 until data.productionCountries.size - 1) {
                    append("${data.productionCountries[0].iso31661}, ")
                }
                append(data.productionCountries[data.productionCountries.size - 1].iso31661)
            } + buildString {
                for (i in 0 until data.genres.size - 1) {
                    append(","+"${data.genres[0].name}, ")
                }
                append(data.genres[data.genres.size - 1].name)
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

    lateinit var trailersData: TrailersResult

    override fun getMovieTrailers(data: ArrayList<TrailersResult>) {
        trailersMoviesAdapter.onItemClick =  {
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=${it.key}"))

            startActivity(intent)
        }
        trailersMoviesAdapter.data = data
    }

    override fun getSimilarMovies(data: ArrayList<Result>) {
        similarMoviesAdapter.onItemClick = {
            val action = MovieFragmentDirections.actionMovieFragmentSelf(it.id.toString())
            findNavController().navigate(action)
        }
        similarMoviesAdapter.data = data
    }

    override fun showError(message: String) {
        Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show()
    }

}