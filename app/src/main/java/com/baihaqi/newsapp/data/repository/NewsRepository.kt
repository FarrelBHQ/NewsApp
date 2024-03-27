package com.baihaqi.newsapp.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.baihaqi.newsapp.data.model.NewsResponse
import com.baihaqi.newsapp.data.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsRepository {

    private val _commonNews = MutableLiveData<NewsResponse>()
    val commonNews: LiveData<NewsResponse> = _commonNews

    private val _aboutAlQuranNews = MutableLiveData<NewsResponse>()
    val aboutAlQuranNews: LiveData<NewsResponse> = _aboutAlQuranNews

    private val _alJazeeraNews = MutableLiveData<NewsResponse>()
    val alJazeeraNews: LiveData<NewsResponse> = _alJazeeraNews

    private val _warningForMuslimNews = MutableLiveData<NewsResponse>()
    val warningForMuslimNews: LiveData<NewsResponse> = _warningForMuslimNews

    private val _searchNews = MutableLiveData<NewsResponse>()
    val searchNews: LiveData<NewsResponse> = _searchNews

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading


    fun getCommonMuslimNews() {
        _isLoading.value = true
        ApiClient.provideApiService().getCommonNews()
            .enqueue(object : Callback<NewsResponse> {
                override fun onResponse(
                    call: Call<NewsResponse>,
                    response: Response<NewsResponse>
                ) {
                    _isLoading.value = false
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            _commonNews.postValue(response.body())
                        }
                    } else {
                        Log.e(
                            "Repository",
                            "onResponse: Call error with HTTP status code" + response.code())
                    }
                }

                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                    Log.e(
                        "Repository",
                        "onFailure" + t.localizedMessage
                    )
                }
            })
    }

    fun getAboutAlQuranNews() {
        _isLoading.value = true
        ApiClient.provideApiService().getAlQuranNews()
            .enqueue(object : Callback<NewsResponse> {
                override fun onResponse(
                    call: Call<NewsResponse>,
                    response: Response<NewsResponse>
                ) {
                    _isLoading.value = false
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            _aboutAlQuranNews.postValue(response.body())
                        }
                    } else {
                        Log.e(
                            "Repository",
                            "onResponse: Call error with HTTP status code" + response.code())
                    }
                }

                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                    Log.e(
                        "Repository",
                        "onFailure" + t.localizedMessage
                    )
                }
            })
    }

    fun getAlJazeeraNews() {
        _isLoading.value = true
        ApiClient.provideApiService().getAlJazeeraNews()
            .enqueue(object : Callback<NewsResponse> {
                override fun onResponse(
                    call: Call<NewsResponse>,
                    response: Response<NewsResponse>
                ) {
                    _isLoading.value = false
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            _alJazeeraNews.postValue(response.body())
                        }
                    } else {
                        Log.e(
                            "Repository",
                            "onResponse: Call error with HTTP status code" + response.code())
                    }
                }

                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                    Log.e(
                        "Repository",
                        "onFailure" + t.localizedMessage
                    )
                }
            })
    }

    fun getWarningForMuslimNews() {
        _isLoading.value = true
        ApiClient.provideApiService().getWarningForMuslimNews()
            .enqueue(object : Callback<NewsResponse> {
                override fun onResponse(
                    call: Call<NewsResponse>,
                    response: Response<NewsResponse>
                ) {
                    _isLoading.value = false
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            _warningForMuslimNews.postValue(response.body())
                        }
                    } else {
                        Log.e(
                            "Repository",
                            "onResponse: Call error with HTTP status code" + response.code())
                    }
                }

                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                    Log.e(
                        "Repository",
                        "onFailure" + t.localizedMessage
                    )
                }
            })
    }

    fun getSearchNews(q: String) {
        _isLoading.value = true
        ApiClient.provideApiService().getSearchNews(q)
            .enqueue(object : Callback<NewsResponse> {
                override fun onResponse(
                    call: Call<NewsResponse>,
                    response: Response<NewsResponse>
                ) {
                    _isLoading.value = false
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            _searchNews.postValue(response.body())
                        }
                    } else {
                        Log.e(
                            "Repository",
                            "onResponse: Call error with HTTP status code" + response.code())
                    }
                }

                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                    Log.e(
                        "Repository",
                        "onFailure" + t.localizedMessage
                    )
                }
            })
    }
}