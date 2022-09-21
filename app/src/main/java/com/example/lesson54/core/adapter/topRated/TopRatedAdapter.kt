package com.example.lesson54.core.adapter.topRated

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson54.core.adapter.popular.PopularMovieListAdapterHolder
import com.example.lesson54.core.models.nowPlaying.NowPlayingResult
import com.example.lesson54.core.models.popular.PopularResult
import com.example.lesson54.core.models.topRated.TopRatedResult
import com.example.lesson54.databinding.ItemMoviesBinding
import com.example.lesson54.databinding.TopRatedMoviesItemBinding

class TopRatedAdapter : RecyclerView.Adapter<TopRatedMovieListAdapterHolder>() {

    var data = ArrayList<TopRatedResult>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field.clear()
            field.addAll(value)
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TopRatedMovieListAdapterHolder = TopRatedMovieListAdapterHolder(
        ItemMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: TopRatedMovieListAdapterHolder, position: Int) {
        holder.bindData(data[position])
    }

    override fun getItemCount(): Int = data.size

}