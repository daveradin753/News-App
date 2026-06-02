package com.newsapplication.mandiri.data.source

import com.newsapplication.mandiri.data.dtos.NewsArticle
import com.newsapplication.mandiri.data.dtos.NewsSource
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("v2/top-headlines/sources")
    suspend fun getSources(
        @Query("category") category: String,
        @Query("apiKey") apiKey: String
    ): NewsSource.Response

    @GET("v2/top-headlines")
    suspend fun getArticles(
        @Query("sources") source: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
        @Query("apiKey") apiKey: String
    ): NewsArticle.Response

}