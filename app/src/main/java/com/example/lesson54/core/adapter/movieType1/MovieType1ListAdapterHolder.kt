package com.example.lesson54.core.adapter.movieType1

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.lesson54.core.models.movieType.Result
import com.example.lesson54.databinding.ItemMoviesBinding
import com.example.lesson54.view.activity.MainActivity

class MovieType1ListAdapterHolder(val binding: ItemMoviesBinding) :
    RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SetTextI18n")
    fun bindData(data: Result) {
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
                for (i in 0 until movieGenres.size - 1) {
                    append("${movieGenres[i]}, ")
                }
                append(movieGenres[movieGenres.size - 1])
            }
        }
    }

}