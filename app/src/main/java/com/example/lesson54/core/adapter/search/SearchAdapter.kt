package com.example.lesson54.core.adapter.search

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson54.core.models.popular.PopularResult
import com.example.lesson54.core.models.topRated.TopRatedResult
import com.example.lesson54.databinding.ItemMoviesBinding
import com.example.lesson54.databinding.ItemSearchBinding

class SearchAdapter : RecyclerView.Adapter<SearchListAdapterHolder>() {

    var onScrollEnd: (() -> Unit)? = null

    var data = ArrayList<PopularResult>()
        set(value) {
            field.addAll(value)
            notifyItemRangeInserted(field.size - value.size, value.size)
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchListAdapterHolder = SearchListAdapterHolder(
        ItemSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: SearchListAdapterHolder, position: Int) {
        holder.bindData(data[position])
        if (position == data.size - 1) {
            onScrollEnd?.invoke()
        }
    }

    override fun getItemCount(): Int = data.size

}