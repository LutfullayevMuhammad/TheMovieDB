package com.example.lesson54.core.adapter.movie.similar

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson54.core.models.movieType.Result
import com.example.lesson54.databinding.ItemMoviesBinding

class SimilarMoviesAdapter : RecyclerView.Adapter<SimilarMoviesAdapterHolder>() {
    var data = ArrayList<Result>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field.clear()
            field.addAll(value)
            notifyDataSetChanged()
        }

    var onItemClick: ((result: Result) -> Unit)? = null

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
        holder.binding.root.setOnClickListener {
            onItemClick?.invoke(data[position])
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}