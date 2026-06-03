package com.newsapplication.mandiri.presentation.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.newsapplication.mandiri.R
import com.newsapplication.mandiri.databinding.ActivitySourcesBinding
import com.newsapplication.mandiri.presentation.helper.SourcesAdapter
import com.newsapplication.mandiri.presentation.viewModel.SourcesViewModel
import com.newsapplication.mandiri.util.showErrorDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SourcesActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySourcesBinding
    private val viewModel by viewModels<SourcesViewModel>()
    private val adapter by lazy { SourcesAdapter() }

    companion object {
        fun instance(context: Context, category: String): Intent {
            val intent = Intent(context, SourcesActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            intent.putExtra("category", category)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySourcesBinding.inflate(layoutInflater)
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
        val category = intent.getStringExtra("category")
        category?.let { viewModel.getSource(it) }
        binding.toolbar.apply {
            title = category?.replaceFirstChar { it.uppercase() } ?: "News Source"
            setNavigationOnClickListener {
                onBackPressedDispatcher.onBackPressed()
            }
        }
        binding.swipeRefresh.apply {
            setOnRefreshListener {
                category?.let { viewModel.getSource(it) }
                isRefreshing = false
            }
        }
        binding.rvSources.apply {
            adapter = this@SourcesActivity.adapter
            layoutManager = LinearLayoutManager(this@SourcesActivity, LinearLayoutManager.VERTICAL, false)
        }
        adapter.setOnItemClickListener { sourceModel ->
            ArticlesActivity.instance(this@SourcesActivity, sourceModel.id)
        }
    }

    private fun setUpObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.source.collect { list ->
                    list?.let {
                        adapter.setSources(it.toMutableList())
                    }
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.error.collect {
                    showErrorDialog(
                        it
                    )
                }
            }
        }
    }
}