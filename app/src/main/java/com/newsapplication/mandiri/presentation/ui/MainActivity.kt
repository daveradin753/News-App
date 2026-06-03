package com.newsapplication.mandiri.presentation.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.newsapplication.mandiri.databinding.ActivityMainBinding
import com.newsapplication.mandiri.presentation.viewModel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<NewsViewModel>()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Use ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupCategoryList()
    }

    private fun setupCategoryList() {
        val categories = listOf(
            "business", "entertainment", "general",
            "health", "science", "sports", "technology"
        )

        // Assuming you create a standard CategoryAdapter
//        val adapter = CategoryAdapter(categories) { selectedCategory ->
//            // Trigger your Paging logic here
//            // viewModel.getArticles(selectedCategory)
//        }
//
//        binding.rvCategories.apply {
//            layoutManager =
//                LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
//            this.adapter = adapter
//        }
    }
}