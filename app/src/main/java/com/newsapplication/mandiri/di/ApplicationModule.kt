package com.newsapplication.mandiri.di

import com.newsapplication.mandiri.data.repository.NewsRepositoryImpl
import com.newsapplication.mandiri.data.source.NewsApiService
import com.newsapplication.mandiri.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    @Singleton
    fun provideNewsRepository(newsApiService: NewsApiService): NewsRepository =
        NewsRepositoryImpl(newsApiService)

}