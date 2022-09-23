package com.example.lesson54.core.adapter.popular

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.lesson54.R
import com.example.lesson54.core.models.popular.PopularResult
import com.example.lesson54.databinding.TopRatedMoviesItemBinding
import com.example.lesson54.view.MainActivity

class PopularMovieListAdapterHolder(val binding: TopRatedMoviesItemBinding) : RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SetTextI18n")
    fun bindData(data: PopularResult) {
        // image
        binding.poster.load("https://image.tmdb.org/t/p/w500" + data.backdropPath)
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
                for (i in 0 until movieGenres.size - 1) {
                    append("${movieGenres[i]}, ")
                }
                append(movieGenres[movieGenres.size - 1])
            }
        }
    }

}