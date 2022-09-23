package com.example.lesson54.core.adapter.search

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.lesson54.core.models.popular.PopularResult
import com.example.lesson54.databinding.ItemSearchBinding

class SearchListAdapterHolder(val binding: ItemSearchBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bindData(data: PopularResult) {
        binding.itemImage.load("https://image.tmdb.org/t/p/w500" + data.posterPath)
        binding.itemTitle.text = data.title
    }

}