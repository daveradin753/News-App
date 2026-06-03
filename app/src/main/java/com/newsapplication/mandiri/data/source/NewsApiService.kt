package com.newsapplication.mandiri.data.source

import com.newsapplication.mandiri.data.dtos.NewsArticle
import com.newsapplication.mandiri.data.dtos.NewsSource
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("top-headlines/sources")
    suspend fun getSources(
        @Query("category") category: String,
    ): NewsSource.Response

    @GET("top-headlines")
    suspend fun getArticles(
        @Query("sources") source: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
        @Query("q") search: String? = null
    ): NewsArticle.Response

}