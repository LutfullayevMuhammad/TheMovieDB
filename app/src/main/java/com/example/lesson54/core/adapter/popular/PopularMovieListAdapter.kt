package com.example.lesson54.core.adapter.popular

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson54.core.models.popular.PopularResult
import com.example.lesson54.databinding.TopRatedMoviesItemBinding

class PopularMovieListAdapter : RecyclerView.Adapter<PopularMovieListAdapterHolder>() {

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
    ): PopularMovieListAdapterHolder = PopularMovieListAdapterHolder(
        TopRatedMoviesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: PopularMovieListAdapterHolder, position: Int) {
        holder.bindData(data[position])
    }

    override fun getItemCount(): Int = data.size

}