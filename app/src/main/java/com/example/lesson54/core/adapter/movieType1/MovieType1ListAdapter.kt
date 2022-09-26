package com.example.lesson54.core.adapter.movieType1

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson54.core.models.movieType.Result
import com.example.lesson54.databinding.ItemMoviesBinding

class MovieType1ListAdapter : RecyclerView.Adapter<MovieType1ListAdapterHolder>() {

    var data = ArrayList<Result>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field.clear()
            field.addAll(value)
            notifyDataSetChanged()
        }

    var onItemClicked: ((result: Result) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieType1ListAdapterHolder =
        MovieType1ListAdapterHolder(
            ItemMoviesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: MovieType1ListAdapterHolder, position: Int) {
        holder.bindData(data = data[position])

        holder.binding.root.setOnClickListener {
            onItemClicked?.invoke(data[position])
        }
    }

    override fun getItemCount(): Int = data.size

}