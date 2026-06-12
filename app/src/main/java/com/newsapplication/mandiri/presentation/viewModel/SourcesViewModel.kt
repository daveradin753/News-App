package com.newsapplication.mandiri.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.newsapplication.mandiri.domain.model.SourceModel
import com.newsapplication.mandiri.domain.repository.NewsRepository
import com.newsapplication.mandiri.util.ApiException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SourcesViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {

    private var _error = Channel<String>()
    val error get() = _error.receiveAsFlow()

    private var _sources = MutableStateFlow<MutableList<SourceModel>?>(null)
    val source get() = _sources.asStateFlow()
    fun getSource(category: String) = viewModelScope.launch(Dispatchers.IO) {
        newsRepository.getSources(category)
            .catch { e ->
                if (e is ApiException) {
                    _error.send(e.message ?: "API Error ${e.code}")
                } else {
                    _error.send(e.message ?: "Unknown error")
                }
            }
            .collect {
                if (it.sources?.isEmpty() == true) {
                    _error.send("No sources found")
                    return@collect
                }
                _sources.emit(it.sources)
            }
    }
}