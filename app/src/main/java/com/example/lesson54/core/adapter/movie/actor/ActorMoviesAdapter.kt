package com.example.lesson54.core.adapter.movie.actor

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson54.core.models.movieActors.Cast
import com.example.lesson54.databinding.ItemActorBinding

class ActorMoviesAdapter : RecyclerView.Adapter<ActorMoviesAdapterHolder>() {
    var data = ArrayList<Cast>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field.clear()
            field.addAll(value)
            notifyDataSetChanged()

        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorMoviesAdapterHolder =
        ActorMoviesAdapterHolder(
            ItemActorBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: ActorMoviesAdapterHolder, position: Int) {
        holder.bindData(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}