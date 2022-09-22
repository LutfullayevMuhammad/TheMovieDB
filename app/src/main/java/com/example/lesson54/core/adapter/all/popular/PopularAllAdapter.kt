package com.example.lesson54.core.adapter.all.popular

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson54.core.adapter.popular.PopularMovieListAdapterHolder
import com.example.lesson54.core.models.nowPlaying.NowPlayingResult
import com.example.lesson54.core.models.popular.PopularResult
import com.example.lesson54.databinding.ItemAllBinding
import com.example.lesson54.databinding.NowPlayingMoviesItemBinding
import com.example.lesson54.databinding.TopRatedMoviesItemBinding

class PopularAllAdapter : RecyclerView.Adapter<PopularAllListAdapterHolder>() {

    var data = ArrayList<PopularResult>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field.clear()
            field.addAll(value)
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PopularAllListAdapterHolder = PopularAllListAdapterHolder(
        ItemAllBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: PopularAllListAdapterHolder, position: Int) {
        holder.bindData(data[position])
    }

    override fun getItemCount(): Int = data.size

}