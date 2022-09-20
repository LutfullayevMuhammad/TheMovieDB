package com.example.lesson54.core.adapter.popular

import android.annotation.SuppressLint
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.lesson54.R
import com.example.lesson54.core.models.movieGenre.Genre
import com.example.lesson54.core.models.movieGenre.MovieGenreResponse
import com.example.lesson54.core.models.topRated.Result
import com.example.lesson54.core.models.topRated.TopRatedMovieResponse
import com.example.lesson54.core.network.MovieAPIClient
import com.example.lesson54.databinding.TopRatedMoviesItemBinding
import com.example.lesson54.view.MainActivity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class PopularMovieListAdapterHolder(val binding: TopRatedMoviesItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SetTextI18n")
    fun bindData(data: Result) {
        // image
        binding.poster.load("https://image.tmdb.org/t/p/w500" + data.posterPath)
        // vote text
        binding.voteAverage.text = data.voteAverage.toString()
        // vote text background
        if (data.voteAverage < 7.0) {
            binding.voteAverage.setBackgroundResource(R.drawable.rate_2_back)
        } else {
            binding.voteAverage.setBackgroundResource(R.drawable.rate_1_back)
        }
        binding.title.text = data.title
        // info
        val date: String = buildString {
            for (i in 0 until 4) {
                append(data.releaseDate[i])
            }
        }
        val movieGenres = ArrayList<String>()
        MainActivity.GENRES_DATA.forEach {
            data.genreIds.forEach { genre ->
                if (genre == it.id) {
                    movieGenres.add(it.name)
                }
            }
        }
        if (binding.info.text == "") {
            binding.info.text = "$date • " + buildString {
//                movieGenres.forEach {
//                    append("$it,")
//                }
                for (i in 0 until movieGenres.size - 1) {
                    append("${movieGenres[i]}, ")
                }
                append(movieGenres[movieGenres.size - 1])
            }
        }
    }

}