package com.example.lesson54.core.network

import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MovieAPIClient {
    companion object {
        fun movieAPI(): MovieAPI {
            return retrofit().create(MovieAPI::class.java)
        }

        private fun retrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
        }
    }
}