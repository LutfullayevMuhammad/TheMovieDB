package com.example.lesson54.core.adapter.movie.trailers

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.lesson54.core.models.movieTrailers.TrailersResult
import com.example.lesson54.core.models.similarMovies.SimilarResult
import com.example.lesson54.databinding.ItemMoviesBinding
import com.example.lesson54.databinding.ItemTrailersBinding
import com.example.lesson54.view.activity.MainActivity

class TrailersMoviesAdapterHolder (val binding: ItemTrailersBinding) :
    RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SetTextI18n")
    fun bindData(data: TrailersResult) {
        binding.itemImage.load("https://image.tmdb.org/t/p/w500" + data.posterPath)
        binding.itemTitle.text = data.title
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
        if (binding.itemSubtitle.text == "") {
            binding.itemSubtitle.text = "$date • " + buildString {
                if (movieGenres.isNotEmpty()) {
                    for (i in 0 until movieGenres.size - 1) {
                        append("${movieGenres[i]}, ")
                    }
                    append(movieGenres[movieGenres.size - 1])
                }
            }
        }
    }

}
