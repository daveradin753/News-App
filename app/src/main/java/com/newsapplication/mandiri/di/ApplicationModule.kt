package com.newsapplication.mandiri.di

import com.newsapplication.mandiri.data.repository.NewsRepositoryImpl
import com.newsapplication.mandiri.domain.repository.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ApplicationModule {

    @Binds
    abstract fun provideNewsRepository(newsRepositoryImpl: NewsRepositoryImpl): NewsRepository

}