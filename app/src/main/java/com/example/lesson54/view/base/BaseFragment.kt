package com.example.lesson54.view.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return getLayout(inflater,parent)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onFragmentReady()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onFragmentCreated()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        onFragmentClosed()
    }

    abstract fun getLayout(inflater: LayoutInflater, container: ViewGroup?):View

    abstract fun onFragmentReady()

    abstract fun onFragmentCreated()

    abstract fun onFragmentClosed()

}