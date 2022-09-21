package com.example.lesson54.view.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lesson54.databinding.FragmentSearchBinding
import com.example.lesson54.view.base.BaseFragment

class SearchFragment : BaseFragment() {
    private lateinit var binding: FragmentSearchBinding
    override fun getLayout(inflater: LayoutInflater, container: ViewGroup?): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onFragmentReady() {

    }

    override fun onFragmentCreated() {

    }

    override fun onFragmentClosed() {

    }
}