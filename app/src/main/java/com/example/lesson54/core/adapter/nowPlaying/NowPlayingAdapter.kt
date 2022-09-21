package com.example.lesson54.core.adapter.nowPlaying

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson54.core.adapter.popular.PopularMovieListAdapterHolder
import com.example.lesson54.core.models.nowPlaying.NowPlayingResult
import com.example.lesson54.core.models.popular.PopularResult
import com.example.lesson54.databinding.NowPlayingMoviesItemBinding
import com.example.lesson54.databinding.TopRatedMoviesItemBinding

class NowPlayingAdapter : RecyclerView.Adapter<NowPlayingMovieListAdapterHolder>() {

    var data = ArrayList<NowPlayingResult>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field.clear()
            field.addAll(value)
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NowPlayingMovieListAdapterHolder = NowPlayingMovieListAdapterHolder(
        NowPlayingMoviesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: NowPlayingMovieListAdapterHolder, position: Int) {
        holder.bindData(data[position])
    }

    override fun getItemCount(): Int = data.size

}