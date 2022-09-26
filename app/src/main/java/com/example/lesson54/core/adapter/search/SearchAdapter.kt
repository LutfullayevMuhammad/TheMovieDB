package com.example.lesson54.core.adapter.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson54.core.models.movieType.Result
import com.example.lesson54.databinding.ItemSearchBinding

class SearchAdapter : RecyclerView.Adapter<SearchAdapterHolder>() {

    var data = ArrayList<Result>()
        set(value) {
            data.clear()
            field.addAll(value)
            notifyDataSetChanged()
        }

    fun addData(data: ArrayList<Result>) {
        this.data.addAll(data)
        notifyItemRangeInserted(this.data.size - data.size, data.size)
    }

    var onScrollEnd: (() -> Unit)? = null

    var onItemClicka: ((result: Result) -> Unit)? = null

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

        holder.binding.root.setOnClickListener {
            onItemClicka?.invoke(data[position])
        }
    }

    override fun getItemCount(): Int = data.size

}