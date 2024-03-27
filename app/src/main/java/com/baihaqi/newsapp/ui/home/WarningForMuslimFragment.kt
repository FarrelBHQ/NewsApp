package com.baihaqi.newsapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.baihaqi.newsapp.adapter.NewsAdapter
import com.baihaqi.newsapp.data.repository.NewsRepository
import com.baihaqi.newsapp.databinding.FragmentWarningForMuslimBinding
import com.baihaqi.newsapp.ui.NewsViewModel
import com.baihaqi.newsapp.utils.NewsViewModelFactory


class WarningForMuslimFragment : Fragment() {
    private lateinit var binding: FragmentWarningForMuslimBinding
    private val warningForMuslimViewModel: NewsViewModel by viewModels {
        NewsViewModelFactory( NewsRepository())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWarningForMuslimBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mAdapter = NewsAdapter()

        warningForMuslimViewModel.getCommonMuslimNews()

        warningForMuslimViewModel.commonNews.observe(viewLifecycleOwner){
            mAdapter.setData(it.articles)
            binding.rvWarningForMuslimNews.apply {
                adapter = mAdapter
                layoutManager = LinearLayoutManager(requireContext())
            }
        }

        warningForMuslimViewModel.isLoading.observe(viewLifecycleOwner){
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