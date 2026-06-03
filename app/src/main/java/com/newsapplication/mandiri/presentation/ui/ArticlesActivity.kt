package com.newsapplication.mandiri.presentation.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.newsapplication.mandiri.databinding.ActivityArticlesBinding
import com.newsapplication.mandiri.presentation.helper.ArticlePagingAdapter
import com.newsapplication.mandiri.presentation.viewModel.ArticleViewModel
import com.newsapplication.mandiri.util.showErrorDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

@AndroidEntryPoint
class ArticlesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityArticlesBinding
    private var source: String? = null
    private var searchJob: Job? = null
    private val viewModel by viewModels<ArticleViewModel>()
    private val adapter by lazy { ArticlePagingAdapter() }

    companion object {
        fun instance(context: Context, source: String?) {
            val intent = Intent(context, ArticlesActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            intent.putExtra("source", source)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityArticlesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setUpUi()
        getArticles()
    }

    private fun setUpUi() {
        binding.apply {
            source = intent.getStringExtra("source")
            toolbar.apply {
                title = "Articles"
                setSupportActionBar(this)
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
            setUpAdapter()
            etSearch.addTextChangedListener { text ->
                searchJob?.cancel()
                searchJob = lifecycleScope.launch {
                    delay(500L.milliseconds)
                    getArticles(text?.toString())
                }
            }
        }

    }

    private fun setUpAdapter() {
        binding.rvArticles.apply {
            this.adapter = this@ArticlesActivity.adapter
            layoutManager = LinearLayoutManager(this@ArticlesActivity, RecyclerView.VERTICAL, false)
        }

        adapter.addLoadStateListener { loadState ->
            binding.apply {
                swipeRefresh.isRefreshing = loadState.source.refresh is LoadState.Loading

                val isListEmpty = loadState.source.refresh is LoadState.NotLoading && adapter.itemCount == 0
                if (isListEmpty) {
                    showErrorDialog(this@ArticlesActivity, "No Data Found")
                }

                val errorState = loadState.source.refresh as? LoadState.Error
                if (errorState != null) {
                    showErrorDialog(this@ArticlesActivity, errorState.error.message.toString())
                }
            }
        }
        adapter.setOnItemClickListener { articleModel ->
            DetailArticleActivity.instance(this@ArticlesActivity, articleModel.url)
        }
    }

    private fun getArticles(search: String? = null) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                source?.let {
                    viewModel.getArticles(it, search)
                        .collectLatest { pagingData ->
                            adapter.submitData(pagingData)
                        }
                }
            }
        }
    }
}