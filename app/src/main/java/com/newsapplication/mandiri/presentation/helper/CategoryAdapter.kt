package com.newsapplication.mandiri.presentation.helper

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.newsapplication.mandiri.databinding.ItemCategoryBinding

class CategoryAdapter: RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    private var categories: List<String> = emptyList()
    @SuppressLint("NotifyDataSetChanged")
    fun setCategories(categories: List<String>) {
        this.categories = categories
        notifyDataSetChanged()
    }

    private var onItemClick: (String) -> Unit = {}
    fun setOnItemClickListener(listener: (String) -> Unit) {
        onItemClick = listener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewHolder {
        val binding = ItemCategoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int,
    ) {
        holder.bind(categories[position])
    }

    override fun getItemCount(): Int = categories.size

    inner class ViewHolder(private val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(category: String) {
            binding.tvCategoryName.text = category.replaceFirstChar { it.uppercase() }
            binding.root.setOnClickListener {
                onItemClick(category)
            }
        }
    }
}