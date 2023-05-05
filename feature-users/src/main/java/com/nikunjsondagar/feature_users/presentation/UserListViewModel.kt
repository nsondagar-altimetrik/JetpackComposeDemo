package com.nikunjsondagar.feature_users.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikunjsondagar.feature_users.R
import com.nikunjsondagar.feature_users.domain.GetUserListUseCase
import com.nikunjsondagar.feature_users.domain.UserDetails
import com.nikunjsondagar.graphql_data.util.NetworkUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val getUserListUseCase: GetUserListUseCase,
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val _state = MutableStateFlow(UserListState())
    val state = _state.asStateFlow()

    private var _searchText: String = "in"

    init {
        updateUserList()
    }

    fun updateUserList() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            if (NetworkUtils.isInternetAvailable(context)) {
                _state.update {
                    it.copy(
                        users = getUserListUseCase(_searchText),
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

    fun updateSearchText(searchText: String) {
        _searchText = searchText
    }

    data class UserListState(
        val isLoading: Boolean = false,
        val users: List<UserDetails> = emptyList(),
        val errorMessage: String? = null
    )

}