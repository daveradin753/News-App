package com.newsapplication.mandiri.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.newsapplication.mandiri.data.dtos.NewsSource
import com.newsapplication.mandiri.data.source.NewsApiService
import com.newsapplication.mandiri.domain.model.ArticleModel
import com.newsapplication.mandiri.domain.repository.NewsRepository
import com.newsapplication.mandiri.util.ApiException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsApiService: NewsApiService
): NewsRepository {

    override fun getSources(
        category: String
    ): Flow<NewsSource.Response> = flow {
        val response = newsApiService.getSources(category)
        when(response.status) {
            "ok" -> emit(response)
            else -> throw ApiException(response.code, response.message)
        }
    }

    override fun getArticlesPaging(source: String): Flow<PagingData<ArticleModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                NewsPagingSource(newsApiService, source)
            }
        ).flow
    }
}
