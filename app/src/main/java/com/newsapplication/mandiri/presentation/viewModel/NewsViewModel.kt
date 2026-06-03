package com.newsapplication.mandiri.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.newsapplication.mandiri.domain.model.ArticleModel
import com.newsapplication.mandiri.domain.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {

    fun getArticles(source: String): Flow<PagingData<ArticleModel>> {
        return newsRepository.getArticlesPaging(source)
            .cachedIn(viewModelScope)
    }
}