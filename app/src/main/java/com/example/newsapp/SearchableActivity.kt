package com.example.newsapp

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.adapter.NewsAdapter
import com.example.newsapp.data.repository.NewsRepository
import com.example.newsapp.databinding.ActivitySearchableBinding
import com.example.newsapp.ui.NewsViewModel
import com.example.newsapp.utils.NewsViewModelFactory

class SearchableActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchableBinding

    private val searchViewModel: NewsViewModel by viewModels {
        NewsViewModelFactory(NewsRepository())
    }
    private var querySearch: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchableBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handleIntentFromMain(intent)

        searchViewModel.searchNews.observe(this@SearchableActivity) {dataSearch ->
            binding.apply {
                if (dataSearch.articles.isEmpty()){
                    tvNoNews.text = "No News About It"
                    tvNoNews.visibility = View.VISIBLE
                } else {
                    rvSearchResults.apply {
                        val mAdapter = NewsAdapter()
                        mAdapter.setData(dataSearch.articles)

                        adapter = mAdapter
                        layoutManager = LinearLayoutManager(this@SearchableActivity)
                        visibility = View.VISIBLE
                    }
                }
            }
        }

        searchViewModel.isLoading.observe(this@SearchableActivity) {
            showLoading(it)
        }

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        binding.searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        setIntent(intent)
        handleIntentFromMain(intent)
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.loadingView.root.visibility = View.VISIBLE
        } else {
            binding.loadingView.root.visibility = View.GONE
        }
    }

    private fun handleIntentFromMain(intent: Intent?) {
        if (Intent.ACTION_SEARCH == intent?.action) {
            intent.getStringExtra(SearchManager.QUERY)?.also { query ->
                querySearch = query

                binding.apply {
                    rvSearchResults.visibility = View.GONE
                    tvNoNews.visibility = View.INVISIBLE
                    searchView.setQuery("", false)
                    searchView.queryHint = query
                    searchView.clearFocus()
                }
                doMySearch(query)
            }
        }

    }

    private fun doMySearch(query: String) {
        searchViewModel.getSearchNews(query)
    }
}