package com.newsapplication.mandiri.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.newsapplication.mandiri.data.source.NewsApiService
import com.newsapplication.mandiri.domain.model.ArticleModel
import com.newsapplication.mandiri.util.ApiException

class NewsPagingSource(
    private val newsApiService: NewsApiService,
    private val source: String
) : PagingSource<Int, ArticleModel>() {

    override fun getRefreshKey(state: PagingState<Int, ArticleModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticleModel> {
        val position = params.key ?: 1
        return try {
            val response = newsApiService.getArticles(
                source = source,
                page = position,
                pageSize = params.loadSize
            )
            val articles = response.articles ?: emptyList()
            if (response.status == "ok") {
                LoadResult.Page(
                    data = articles,
                    prevKey = if (position == 1) null else position - 1,
                    nextKey = if (articles.isEmpty()) null else position + 1
                )
            } else {
                LoadResult.Error(ApiException(response.code, response.message))
            }
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }
}
