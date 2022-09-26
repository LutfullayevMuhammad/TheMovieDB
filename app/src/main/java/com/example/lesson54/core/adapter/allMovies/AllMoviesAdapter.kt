package com.example.lesson54.core.adapter.allMovies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson54.core.models.movieType.Result
import com.example.lesson54.databinding.ItemAllBinding
import com.example.lesson54.databinding.ItemMoviesBinding

class AllMoviesAdapter : RecyclerView.Adapter<AllMoviesAdapterHolder>() {

    var data = ArrayList<Result>()
        set(value) {
            field.addAll(value)
            notifyItemRangeInserted(data.size - value.size, value.size)
        }
    var onScrollEnd: (() -> Unit)? = null

    var onItemClicka: ((result: Result) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllMoviesAdapterHolder =
        AllMoviesAdapterHolder(
            ItemAllBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: AllMoviesAdapterHolder, position: Int) {
        holder.bindData(data = data[position])
        if (position == data.size - 1) {
            onScrollEnd?.invoke()
        }

        holder.binding.root.setOnClickListener {
            onItemClicka?.invoke(data[position])
        }
    }

    override fun getItemCount(): Int = data.size

}