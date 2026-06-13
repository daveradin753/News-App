package com.newsapplication.mandiri.domain.usecase

import androidx.paging.PagingData
import com.newsapplication.mandiri.domain.model.ArticleModel
import com.newsapplication.mandiri.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetArticlesUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    operator fun invoke(source: String, search: String? = null): Flow<PagingData<ArticleModel>> {
        return repository.getArticlesPaging(source, search)
    }
}
