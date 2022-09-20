package com.example.lesson54.core.presenter

import com.example.lesson54.core.models.topRated.Result

interface Presenter {
    interface Presenter {
        fun loadData()
        fun refreshData()
    }

    interface View {
        fun dataState(isLoading: Boolean)
        fun showData(data: ArrayList<Result>)
        fun showError(message: String)
    }
}