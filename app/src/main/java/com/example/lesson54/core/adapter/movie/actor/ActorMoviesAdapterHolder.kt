package com.example.lesson54.core.adapter.movie.actor

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.lesson54.R
import com.example.lesson54.core.models.movieActors.Cast
import com.example.lesson54.core.models.movieType.Result
import com.example.lesson54.databinding.ItemActorBinding
import com.example.lesson54.databinding.PopularMoviesItemBinding
import com.example.lesson54.view.activity.MainActivity

class ActorMoviesAdapterHolder (val binding: ItemActorBinding) :
    RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SetTextI18n")
    fun bindData(data: Cast) {
        // image
        binding.itemImage.load("https://image.tmdb.org/t/p/w500" + data.profilePath)
        // name
        binding.itemName.text = data.name
        // character
        binding.itemCharacter.text = data.character


    }

}
