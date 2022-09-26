package com.example.lesson54.view.search

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lesson54.core.adapter.search.SearchAdapter
import com.example.lesson54.core.models.movieType.Result
import com.example.lesson54.core.presenter.HomePresenter
import com.example.lesson54.core.presenter.SearchPresenterImp
import com.example.lesson54.databinding.FragmentSearchBinding
import com.example.lesson54.view.base.BaseFragment
import com.example.lesson54.view.home.HomeFragmentDirections
import java.util.*
import kotlin.collections.ArrayList

class SearchFragment : BaseFragment(), HomePresenter.View {

    private lateinit var binding: FragmentSearchBinding
    private val adapter = SearchAdapter()
    private var presenter: HomePresenter.Presenter? = null
    private var searchedText = ""

    override fun getLayout(inflater: LayoutInflater, container: ViewGroup?): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onFragmentReady() {
        presenter = SearchPresenterImp(this@SearchFragment,)

        // preparing list
        binding.searchingList.adapter = adapter
        binding.searchingList.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        // loading data
        binding.searchView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                presenter?.searchData(p0.toString())
                searchedText = p0.toString()
            }
        })
        // loading actions
        adapter.onScrollEnd = {
            if (searchedText.isNotBlank()) {
                presenter?.loadNextData(searchedText)
            }
        }
        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }

        adapter.onItemClicka = {
            val action = SearchFragmentDirections.actionSearchFragmentToMovieFragment("${it.id}")
            findNavController().navigate(action)
        }

    }

    override fun onFragmentCreated() {}

    override fun onFragmentClosed() {
        presenter?.cancel()
    }

    override fun dataState(isLoading: Boolean) {}

    override fun getData(data: ArrayList<Result>) {
        adapter.data = data
    }

    override fun setNextData(data: ArrayList<Result>) {
        adapter.addData(data)
    }

    override fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }
}