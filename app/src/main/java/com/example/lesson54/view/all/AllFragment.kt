package com.example.lesson54.view.all

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.lesson54.core.adapter.allMovies.AllMoviesAdapter
import com.example.lesson54.core.models.movieType.Result
import com.example.lesson54.core.presenter.AllPresenterImp
import com.example.lesson54.core.presenter.HomePresenter
import com.example.lesson54.databinding.FragmentAllBinding
import com.example.lesson54.view.base.BaseFragment
import kotlin.collections.ArrayList

class AllFragment : BaseFragment(), HomePresenter.View {

    private lateinit var binding: FragmentAllBinding
    private val args: AllFragmentArgs by navArgs()
    private val adapter = AllMoviesAdapter()
    private var presenter: HomePresenter.Presenter? = null
    private var pageNumber = 1

    override fun getLayout(inflater: LayoutInflater, container: ViewGroup?): View {
        binding = FragmentAllBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onFragmentReady() {
        // preparing list
        binding.list.adapter = adapter
        binding.list.layoutManager = GridLayoutManager(requireContext(), 2)
        // loading data
        when (args.movieType) {
            "popular" -> binding.screenTitle.text = "POPULAR"
            "top_rated" -> binding.screenTitle.text = "TOP RATED"
            "now_playing" -> binding.screenTitle.text = "NOW PLAYING"
            "upcoming" -> binding.screenTitle.text = "UPCOMING"
        }
        presenter =
            AllPresenterImp(view = this, movieType = args.movieType, page = pageNumber.toString())
        presenter?.loadData()
        pageNumber++
        // loading actions
        adapter.onScrollEnd = {
            presenter = AllPresenterImp(
                view = this,
                movieType = args.movieType,
                page = pageNumber.toString()
            )
            presenter?.loadData()
            pageNumber++
        }
    }

    override fun onFragmentCreated() {}

    override fun onFragmentClosed() {
        presenter?.cancel()
    }

    override fun dataState(isLoading: Boolean) {
    }

    override fun getData(data: ArrayList<Result>) {
        adapter.data = data
    }

    override fun showError(message: String) {
        Toast.makeText(requireContext(), "On loading data $message occurred", Toast.LENGTH_LONG)
            .show()
    }
}