package com.example.lesson54.core.adapter.movieType2

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson54.core.models.movieType.Result
import com.example.lesson54.databinding.PopularMoviesItemBinding

class MovieType2ListAdapter : RecyclerView.Adapter<MovieType2ListAdapterHolder>() {

    var data = ArrayList<Result>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field.clear()
            field.addAll(value)
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieType2ListAdapterHolder = MovieType2ListAdapterHolder(
        PopularMoviesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: MovieType2ListAdapterHolder, position: Int) {
        holder.bindData(data = data[position])
    }

    override fun getItemCount(): Int = data.size

}