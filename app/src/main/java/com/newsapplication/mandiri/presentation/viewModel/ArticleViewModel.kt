package com.newsapplication.mandiri.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.newsapplication.mandiri.domain.model.ArticleModel
import com.newsapplication.mandiri.domain.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(
    private val newsRepository: NewsRepository
): ViewModel() {

    private var _error = Channel<String>()
    val error get() = _error.receiveAsFlow()

    fun getArticles(source: String, search: String? = null): Flow<PagingData<ArticleModel>> {
        return newsRepository.getArticlesPaging(source, search)
            .cachedIn(viewModelScope)
    }

}