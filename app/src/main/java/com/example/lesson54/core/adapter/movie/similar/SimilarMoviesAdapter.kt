package com.example.lesson54.core.adapter.movie.similar

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson54.core.models.similarMovies.SimilarResult
import com.example.lesson54.databinding.ItemMoviesBinding

class SimilarMoviesAdapter : RecyclerView.Adapter<SimilarMoviesAdapterHolder>() {
    var data = ArrayList<SimilarResult>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field.clear()
            field.addAll(value)
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimilarMoviesAdapterHolder =
        SimilarMoviesAdapterHolder(
            ItemMoviesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: SimilarMoviesAdapterHolder, position: Int) {
        holder.bindData(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}