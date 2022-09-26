package com.example.lesson54.core.adapter.movie.trailers

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.lesson54.core.models.movieTrailers.TrailersResult
import com.example.lesson54.databinding.ItemMoviesBinding
import com.example.lesson54.databinding.ItemTrailersBinding
import com.example.lesson54.view.activity.MainActivity

class TrailersMoviesAdapterHolder (val binding: ItemTrailersBinding) :
    RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SetTextI18n")
    fun bindData(data: TrailersResult) {
        binding.itemImage.load("https://img.youtube.com/vi/${data.key}/mqdefault.jpg")
    }

}
