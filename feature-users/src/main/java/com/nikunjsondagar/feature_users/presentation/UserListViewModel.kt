package com.nikunjsondagar.feature_users.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikunjsondagar.feature_users.domain.GetUserListUseCase
import com.nikunjsondagar.feature_users.domain.UserDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val getUserListUseCase: GetUserListUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(UserListState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            _state.update { it.copy(users = getUserListUseCase(""), isLoading = false) }
        }
    }

    data class UserListState(
        val isLoading: Boolean = false,
        val users: List<UserDetails> = emptyList()
    )

}