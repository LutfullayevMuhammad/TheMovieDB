package com.example.lesson54.core.adapter.movie.trailers

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson54.core.models.movieTrailers.TrailersResult
import com.example.lesson54.core.models.similarMovies.SimilarResult
import com.example.lesson54.databinding.ItemMoviesBinding
import com.example.lesson54.databinding.ItemTrailersBinding

class TrailersMoviesAdapter : RecyclerView.Adapter<TrailersMoviesAdapterHolder>() {
    var data = ArrayList<TrailersResult>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field.clear()
            field.addAll(value)
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrailersMoviesAdapterHolder =
        TrailersMoviesAdapterHolder(
            ItemTrailersBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: TrailersMoviesAdapterHolder, position: Int) {
        holder.bindData(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}