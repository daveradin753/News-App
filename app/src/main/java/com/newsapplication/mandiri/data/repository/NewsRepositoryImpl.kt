package com.newsapplication.mandiri.data.repository

import com.newsapplication.mandiri.data.dtos.NewsArticle
import com.newsapplication.mandiri.data.dtos.NewsSource
import com.newsapplication.mandiri.data.source.NewsApiService
import com.newsapplication.mandiri.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.newSingleThreadContext
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsApiService: NewsApiService
): NewsRepository {

    override fun getSources(
        category: String,
        apiKey: String
    ): Flow<NewsSource.Response> = flow {
        val response = newsApiService.getSources(category, apiKey)
        when(response.status) {
            "ok" -> emit(response)
            else -> throw Exception(response.message)
        }
    }

    override fun getArticles(
        source: String,
        page: Int,
        pageSize: Int,
        apiKey: String
    ): Flow<NewsArticle.Response> {
        TODO("Not yet implemented")
    }


}