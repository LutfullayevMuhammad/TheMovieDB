package com.example.lesson54.core.adapter.all.nowPlaying

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.lesson54.R
import com.example.lesson54.core.models.nowPlaying.NowPlayingResult
import com.example.lesson54.core.models.popular.PopularResult
import com.example.lesson54.databinding.ItemAllBinding
import com.example.lesson54.databinding.NowPlayingMoviesItemBinding
import com.example.lesson54.databinding.TopRatedMoviesItemBinding
import com.example.lesson54.view.MainActivity

class NowPlayingAllListAdapterHolder(val binding: ItemAllBinding):RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SetTextI18n")
    fun bindData(data: NowPlayingResult) {
        binding.allImage.load("https://image.tmdb.org/t/p/w500" + data.posterPath)
        binding.allTitle.text = data.title
    }

}