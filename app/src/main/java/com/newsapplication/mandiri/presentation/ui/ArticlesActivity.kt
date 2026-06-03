package com.newsapplication.mandiri.presentation.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.newsapplication.mandiri.R
import com.newsapplication.mandiri.databinding.ActivityArticlesBinding
import com.newsapplication.mandiri.presentation.viewModel.ArticleViewModel

class ArticlesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityArticlesBinding
    private val viewModel by viewModels<ArticleViewModel>()

    companion object {
        fun instance(context: Context, source: String?): Intent {
            val intent = Intent(context, ArticlesActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            intent.putExtra("source", source)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityArticlesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setUpObserver()
        setUpUi()
    }

    private fun setUpUi() {
        binding.apply {
            toolbar.apply {
                title = "Articles"
                setNavigationOnClickListener {
                    onBackPressedDispatcher.onBackPressed()
                }
            }
            swipeRefresh.apply {
                setOnRefreshListener {
                    getArticles()
                    isRefreshing = false
                }
            }
        }
    }

    private fun getArticles() {

    }

    private fun setUpObserver() {

    }
}