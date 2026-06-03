package com.newsapplication.mandiri.presentation.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.newsapplication.mandiri.R
import com.newsapplication.mandiri.databinding.ActivityMainBinding
import com.newsapplication.mandiri.presentation.helper.CategoryAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val adapter by lazy { CategoryAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupCategoryList()
    }

    private fun setupCategoryList() {
        val categories = listOf(
            "business", "entertainment", "general",
            "health", "science", "sports", "technology"
        )
        adapter.setCategories(categories)
        binding.rvCategory.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            this.adapter = adapter
        }
        adapter.setOnItemClickListener { selectedCategory ->
            // Trigger your Paging logic here
            SourcesActivity.instance(this@MainActivity, selectedCategory)
        }
    }
}