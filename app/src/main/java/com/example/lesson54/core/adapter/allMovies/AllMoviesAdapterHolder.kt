package com.example.lesson54.core.adapter.allMovies

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.lesson54.core.models.movieType.Result
import com.example.lesson54.databinding.ItemAllBinding
import com.example.lesson54.view.activity.MainActivity

class AllMoviesAdapterHolder(val binding: ItemAllBinding) : RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SetTextI18n")
    fun bindData(data: Result) {
        binding.allImage.load("https://image.tmdb.org/t/p/w500" + data.posterPath)
        binding.allTitle.text = data.title
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
        if (binding.allSubtitle.text == "") {
            binding.allSubtitle.text = "$date • " + buildString {
                for (i in 0 until movieGenres.size - 1) {
                    append("${movieGenres[i]}, ")
                }
                append(movieGenres[movieGenres.size - 1])
            }
        }
    }

}