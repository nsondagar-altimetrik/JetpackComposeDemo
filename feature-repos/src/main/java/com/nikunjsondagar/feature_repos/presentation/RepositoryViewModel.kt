package com.nikunjsondagar.feature_repos.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikunjsondagar.feature_repos.domain.Repository
import com.nikunjsondagar.feature_repos.domain.RepositoryListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepositoryViewModel @Inject constructor(
    private val getRepositoryUseCase: RepositoryListUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(GetRepositoryListState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            _state.update { it.copy(repositoryList = getRepositoryUseCase(""), isLoading = false) }
        }
    }


    data class GetRepositoryListState(
        var repositoryList: List<Repository> = emptyList(),
        val isLoading: Boolean = false
    )

}