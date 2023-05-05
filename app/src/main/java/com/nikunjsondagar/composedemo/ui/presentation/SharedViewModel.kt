package com.nikunjsondagar.composedemo.ui.presentation

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val _searchViewState: MutableState<SearchViewState> =
        mutableStateOf(value = SearchViewState.SEARCH_CLOSED)
    val searchViewState: State<SearchViewState> = _searchViewState

    private val _searchTab: MutableState<SearchTab> = mutableStateOf(value = SearchTab.USERS)
    val searchTabState: State<SearchTab> = _searchTab

    private var _searchText: MutableState<String> = mutableStateOf("")
    val searchText: State<String> = _searchText

    private val _buttonClicked: MutableState<Boolean> = mutableStateOf(false)
    val buttonClicked: State<Boolean> = _buttonClicked

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


    fun isInternetAvailable(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val nw = cm.activeNetwork ?: return false
            val actNw = cm.getNetworkCapabilities(nw) ?: return false
            return when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
                else -> false
            }
        } else {
            return cm.activeNetworkInfo?.isConnected ?: false
        }
    }

    enum class SearchTab {
        USERS, REPOS
    }

    enum class SearchViewState {
        SEARCH_ACTIVE,
        SEARCH_CLOSED
    }

}