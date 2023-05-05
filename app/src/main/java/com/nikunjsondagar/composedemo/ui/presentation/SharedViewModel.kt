package com.nikunjsondagar.composedemo.ui.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {

    private val _searchViewState: MutableState<SearchViewState> =
        mutableStateOf(value = SearchViewState.SEARCH_CLOSED)
    val searchViewState: State<SearchViewState> = _searchViewState

    private val _searchTab: MutableState<SearchTab> = mutableStateOf(value = SearchTab.USERS)
    val searchTabState: State<SearchTab> = _searchTab

    private var _searchText: MutableState<String> = mutableStateOf("")
    val searchText: State<String> = _searchText

    private val _buttonClicked : MutableState<Boolean> = mutableStateOf(false)
    val buttonClicked : State<Boolean> = _buttonClicked

    fun updatesSearchViewState(newValue: SearchViewState) {
        _searchViewState.value = newValue
    }

    fun updateSelectedTab(newValue: SearchTab) {
        _searchTab.value = newValue
    }

    fun updateSearchText(newValue: String) {
        _searchText.value = newValue
    }

    fun clickButton(newValue: Boolean) {
        _buttonClicked.value = newValue
    }

    enum class SearchTab {
        USERS, REPOS
    }

    enum class SearchViewState {
        SEARCH_ACTIVE,
        SEARCH_CLOSED
    }

}