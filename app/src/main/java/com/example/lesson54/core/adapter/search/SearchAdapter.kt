package com.example.lesson54.core.adapter.search

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson54.core.models.movieType.Result
import com.example.lesson54.databinding.ItemSearchBinding

class SearchAdapter : RecyclerView.Adapter<SearchAdapterHolder>() {

    var data = ArrayList<Result>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field.clear()
            field.addAll(value)
            notifyDataSetChanged()
        }
    var onScrollEnd: (() -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchAdapterHolder =
        SearchAdapterHolder(
            ItemSearchBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: SearchAdapterHolder, position: Int) {
        holder.bindData(data = data[position])
        if (position == data.size - 1) {
            onScrollEnd?.invoke()
        }
    }

    override fun getItemCount(): Int = data.size

}