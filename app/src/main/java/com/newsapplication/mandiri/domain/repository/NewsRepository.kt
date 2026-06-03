package com.newsapplication.mandiri.domain.repository

import androidx.paging.PagingData
import com.newsapplication.mandiri.data.dtos.NewsArticle
import com.newsapplication.mandiri.data.dtos.NewsSource
import com.newsapplication.mandiri.domain.model.ArticleModel
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getSources(category: String): Flow<NewsSource.Response>
    fun getArticlesPaging(source: String): Flow<PagingData<ArticleModel>>
}
