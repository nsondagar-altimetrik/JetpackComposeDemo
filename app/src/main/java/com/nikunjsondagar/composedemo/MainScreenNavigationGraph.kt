package com.nikunjsondagar.composedemo

import android.app.Activity
import android.os.Bundle
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nikunjsondagar.feature_repos.presentation.RepositoryListScreen
import com.nikunjsondagar.feature_repos.presentation.RepositoryViewModel
import com.nikunjsondagar.feature_settings.SettingScreen
import com.nikunjsondagar.feature_users.presentation.DisplayUsersList
import com.nikunjsondagar.feature_users.presentation.UserListViewModel

@Composable
fun MainScreenNavigationGraph(
    navHostController: NavHostController,
    padding: PaddingValues,
    activity: Activity,
    sharedViewModel: SharedViewModel
) {
    val userListViewModel = hiltViewModel<UserListViewModel>()
    NavHost(
        navController = navHostController,
        startDestination = NavigationScreens.UserScreen.route,
        modifier = Modifier.padding(padding)
    ) {
        composable(route = NavigationScreens.UserScreen.route) {
            sharedViewModel.updateSelectedTab(SharedViewModel.SearchTab.USERS)
            val state by userListViewModel.state.collectAsState()
            DisplayUsersList(state) { profileURL ->
                openWebViewActivity(activity, profileURL)
            }
            if (sharedViewModel.buttonClicked.value) {
                LaunchedEffect(key1 = "key1", block = {
                    userListViewModel.updateUserList(sharedViewModel.searchText.value)
                })
                sharedViewModel.clickButton(false)
            }
        }
        composable(route = NavigationScreens.RepoScreen.route) {
            sharedViewModel.updatesSearchViewState(SharedViewModel.SearchViewState.SEARCH_CLOSED)
            sharedViewModel.updateSelectedTab(SharedViewModel.SearchTab.REPOS)
            val repositoryListViewModel = hiltViewModel<RepositoryViewModel>()
            val state by repositoryListViewModel.state.collectAsState()
            RepositoryListScreen(state) { repoURL ->
                openWebViewActivity(activity, repoURL)
            }
        }
        composable(route = NavigationScreens.SettingScreen.route) {
            SettingScreen()
        }
    }
}

private fun openWebViewActivity(activity: Activity, url: String) {
    WebViewActivity.startWebViewActivity(activity, Bundle().apply {
        putString(WebViewActivity.ProfileURL, url)
    })
}