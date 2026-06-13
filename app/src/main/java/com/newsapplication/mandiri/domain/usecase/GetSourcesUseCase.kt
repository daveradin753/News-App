package com.newsapplication.mandiri.domain.usecase

import com.newsapplication.mandiri.data.dtos.NewsSource
import com.newsapplication.mandiri.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSourcesUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    operator fun invoke(category: String): Flow<NewsSource.Response> {
        return repository.getSources(category)
    }
}
