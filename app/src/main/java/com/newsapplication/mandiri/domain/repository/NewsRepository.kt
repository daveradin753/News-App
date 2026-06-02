package com.newsapplication.mandiri.domain.repository

import com.newsapplication.mandiri.data.dtos.NewsArticle
import com.newsapplication.mandiri.data.dtos.NewsSource
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun getSources(category: String, apiKey: String): Flow<NewsSource.Response>
    fun getArticles(source: String, page: Int, pageSize: Int, apiKey: String): Flow<NewsArticle.Response>
}