package com.example.newsapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.R
import com.example.newsapp.adapter.NewsAdapter
import com.example.newsapp.data.repository.NewsRepository
import com.example.newsapp.databinding.FragmentAboutAlQuranBinding
import com.example.newsapp.databinding.FragmentAlJazeeraBinding
import com.example.newsapp.ui.NewsViewModel
import com.example.newsapp.utils.NewsViewModelFactory


class AlJazeeraFragment : Fragment() {
    private lateinit var binding: FragmentAlJazeeraBinding
    private val alJazeeraViewModel: NewsViewModel by viewModels {
        NewsViewModelFactory( NewsRepository())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAlJazeeraBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mAdapter = NewsAdapter()

        alJazeeraViewModel.getCommonMuslimNews()

        alJazeeraViewModel.commonNews.observe(viewLifecycleOwner){
            mAdapter.setData(it.articles)
            binding.rvAlJazeeraNews.apply {
                adapter = mAdapter
                layoutManager = LinearLayoutManager(requireContext())
            }
        }

        alJazeeraViewModel.isLoading.observe(viewLifecycleOwner){
            isLoading(it)
        }

    }

    private fun isLoading(it: Boolean) {
        if (it) {
            binding.loadingView.root.visibility = View.VISIBLE
        } else {
            binding.loadingView.root.visibility = View.GONE
        }
    }
}