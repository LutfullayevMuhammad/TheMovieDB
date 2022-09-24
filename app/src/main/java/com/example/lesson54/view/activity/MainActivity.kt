package com.example.lesson54.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lesson54.core.models.movieGenre.Genre
import com.example.lesson54.databinding.ActivityMainBinding
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    companion object {
        var GENRES_DATA = ArrayList<Genre>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}