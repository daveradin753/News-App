package com.newsapplication.mandiri.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.newsapplication.mandiri.domain.model.ArticleModel
import com.newsapplication.mandiri.domain.usecase.GetArticlesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(
    private val getArticlesUseCase: GetArticlesUseCase
): ViewModel() {

    fun getArticles(source: String, search: String? = null): Flow<PagingData<ArticleModel>> {
        return getArticlesUseCase(source, search)
            .cachedIn(viewModelScope)
    }

}