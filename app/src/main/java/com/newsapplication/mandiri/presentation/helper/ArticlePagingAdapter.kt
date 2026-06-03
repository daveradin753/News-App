package com.newsapplication.mandiri.presentation.helper

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.newsapplication.mandiri.databinding.ItemArticleBinding
import com.newsapplication.mandiri.domain.model.ArticleModel

class ArticlePagingAdapter(
    private val onItemClick: (ArticleModel) -> Unit
) : PagingDataAdapter<ArticleModel, ArticlePagingAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemArticleBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = getItem(position)
        if (article != null) {
            holder.bind(article)
        }
    }

    inner class ViewHolder(private val binding: ItemArticleBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(article: ArticleModel) {
            binding.apply {
                tvArticleTitle.text = article.title
                tvArticleDescription.text = article.description
                tvArticleDate.text = article.publishedAt?.split("T")?.get(0) ?: ""

                Glide.with(itemView.context)
                    .load(article.urlToImage)
                    .into(ivArticleImage)

                root.setOnClickListener {
                    onItemClick(article)
                }
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ArticleModel>() {
            override fun areItemsTheSame(oldItem: ArticleModel, newItem: ArticleModel): Boolean {
                return oldItem.url == newItem.url
            }

            override fun areContentsTheSame(oldItem: ArticleModel, newItem: ArticleModel): Boolean {
                return oldItem == newItem
            }
        }
    }
}
