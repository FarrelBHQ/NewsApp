package com.baihaqi.newsapp.ui.detail

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.baihaqi.newsapp.data.model.ArticlesItem
import com.baihaqi.newsapp.databinding.ActivityDetailNewsBinding
import com.squareup.picasso.Picasso

class DetailNewsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailNewsBinding

    companion object{
        const val DATA_NEWS = "data_news"
        const val DATE_NEWS = "date_news"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarDetail)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = "Detail Berita"
        }

        val dataNews = intent.getParcelableExtra<ArticlesItem>(DATA_NEWS)
        val dateNews = intent.getStringExtra(DATE_NEWS)

        setupMyXml(dataNews, dateNews)
        setWebView(dataNews)

    }

    private fun setWebView(dataNews: ArticlesItem?) {
        val webSetting = binding.wvDetail.settings
        webSetting.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW

        binding.wvDetail.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                binding.loadingView.root.visibility = View.VISIBLE
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                binding.loadingView.root.visibility = View.GONE
            }
        }
        dataNews?.url?.let { binding.wvDetail.loadUrl(it) }
    }

    private fun setupMyXml(dataNews: ArticlesItem?, dateNews: String?) {
        binding.apply {
            detailTitle.text = dataNews?.title
            detailAuthor.text = dataNews?.author
            detailPublishedAt.text = dateNews
            detailLink.text = dataNews?.url
            detailLink.setOnClickListener {
                intent = Intent(Intent.ACTION_VIEW)
                intent.setData(Uri.parse(detailLink.text as String?))
                startActivity(intent)
                }


            Picasso.get()
                .load(dataNews?.urlToImage)
                .fit()
                .centerInside()
                .into(detailImage)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}