package com.example.lesson54.core.models

abstract class BaseType{
    abstract fun getType() :Int

    companion object{
        val TYPE_Popular = 0
        val TYPE_TopRated = 1
        val TYPE_Upcoming = 2
        val TYPE_NowPlaying = 3
    }
}