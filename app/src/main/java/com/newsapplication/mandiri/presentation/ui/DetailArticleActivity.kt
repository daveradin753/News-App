package com.newsapplication.mandiri.presentation.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.webkit.WebViewClient
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.newsapplication.mandiri.databinding.ActivityDetailArticleBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailArticleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailArticleBinding

    companion object {
        fun instance(context: Context, articleUrl: String?) {
            val intent = Intent(context, DetailArticleActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            intent.putExtra("article_url", articleUrl)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setUpUi()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setUpUi() {
        binding.apply {
            toolbar.apply {
                title = "Detail Article"
                setSupportActionBar(this)
                setNavigationOnClickListener {
                    onBackPressedDispatcher.onBackPressed()
                }
            }

            val articleUrl = intent.getStringExtra("article_url")
            val secureUrl = articleUrl?.replace("http://", "https://")
            secureUrl?.let { webView.loadUrl(it) }
            webView.apply {
                settings.javaScriptEnabled = true
                webViewClient = WebViewClient()
                articleUrl?.let {
                    loadUrl(it)
                }
            }
        }
    }
}