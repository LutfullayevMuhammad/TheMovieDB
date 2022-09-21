package com.example.lesson54.core.adapter.upcoming

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.lesson54.R
import com.example.lesson54.core.models.popular.PopularResult
import com.example.lesson54.core.models.topRated.TopRatedResult
import com.example.lesson54.core.models.upcoming.UpcomingResult
import com.example.lesson54.databinding.ItemMoviesBinding
import com.example.lesson54.view.MainActivity

class UpcomingMovieListAdapterHolder(val binding: ItemMoviesBinding):RecyclerView.ViewHolder(binding.root) {

    fun bindData(data: UpcomingResult) {
        binding.itemImage.load("https://image.tmdb.org/t/p/w500" + data.posterPath)
        binding.itemTitle.text = data.title
    }

}