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
    ): SearchListAdapterHolder = SearchListAdapterHolder(
        ItemSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: SearchListAdapterHolder, position: Int) {
        holder.bindData(data[position])
        Log.i("TAG", position.toString())
    }

    override fun getItemCount(): Int = data.size

}