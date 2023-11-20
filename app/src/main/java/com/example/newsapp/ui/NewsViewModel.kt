package com.example.newsapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.newsapp.data.model.NewsResponse
import com.example.newsapp.data.repository.NewsRepository

class NewsViewModel(private val repository: NewsRepository) : ViewModel() {
    val commonNews: LiveData<NewsResponse> = repository.commonNews
    val alJazeeraNews: LiveData<NewsResponse> = repository.alJazeeraNews
    val aboutAlQuranNews: LiveData<NewsResponse> = repository.aboutAlQuranNews
    val warningForMuslimNews: LiveData<NewsResponse> = repository.warningForMuslimNews
    val searchNews: LiveData<NewsResponse> = repository.searchNews
    val isLoading: LiveData<Boolean> = repository.isLoading

    fun getCommonMuslimNews() {
        repository.getCommonMuslimNews()
    }


    fun getAboutAlQuranNews() {
        repository.getAboutAlQuranNews()
    }


    fun getAlJazeeraNews() {
        repository.getAlJazeeraNews()
    }


    fun getWarningNews() {
        repository.getWarningForMuslimNews()
    }


    fun getSearchNews(q: String) {
        repository.getSearchNews(q)
    }

}