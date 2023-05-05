package com.nikunjsondagar.feature_repos.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikunjsondagar.feature_repos.R
import com.nikunjsondagar.feature_repos.domain.Repository
import com.nikunjsondagar.feature_repos.domain.RepositoryListUseCase
import com.nikunjsondagar.graphql_data.util.NetworkUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepositoryViewModel @Inject constructor(
    private val getRepositoryUseCase: RepositoryListUseCase,
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val _state = MutableStateFlow(GetRepositoryListState())
    val state = _state.asStateFlow()

    private var _searchText: String = "in"

    init {
        updateRepositoryList()
    }

    fun updateSearchText(newSearchText: String) {
        _searchText = newSearchText
    }

    fun updateRepositoryList() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            if (NetworkUtils.isInternetAvailable(context)) {
                _state.update {
                    it.copy(
                        repositoryList = getRepositoryUseCase(_searchText),
                        isLoading = false,
                        errorMessage = null
                    )
                }
            } else {
                _state.update {
                    it.copy(
                        errorMessage = context.getString(R.string.no_internet_connection_error_text),
                        isLoading = false
                    )
                }
            }
        }
    }


    data class GetRepositoryListState(
        var repositoryList: List<Repository> = emptyList(),
        val isLoading: Boolean = false,
        var errorMessage: String? = null
    )

}