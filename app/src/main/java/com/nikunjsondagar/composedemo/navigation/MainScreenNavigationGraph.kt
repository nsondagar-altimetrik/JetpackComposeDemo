package com.nikunjsondagar.composedemo.navigation

import android.app.Activity
import android.os.Bundle
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nikunjsondagar.composedemo.ui.presentation.SharedViewModel
import com.nikunjsondagar.composedemo.ui.presentation.WebViewActivity
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
            DisplayUsersList(userListViewModel) { profileURL ->
                openWebViewActivity(activity, profileURL)
            }
            if (sharedViewModel.buttonClicked.value) {
                LaunchedEffect(key1 = "key1", block = {
                    userListViewModel.updateSearchText(sharedViewModel.searchText.value)
                    userListViewModel.updateUserList()
                })
                sharedViewModel.clickButton(false)
            }
        }
        composable(route = NavigationScreens.RepoScreen.route) {
            sharedViewModel.updatesSearchViewState(SharedViewModel.SearchViewState.SEARCH_CLOSED)
            sharedViewModel.updateSelectedTab(SharedViewModel.SearchTab.REPOS)
            val repositoryListViewModel = hiltViewModel<RepositoryViewModel>()
            RepositoryListScreen(repositoryListViewModel) { repoURL ->
                openWebViewActivity(activity, repoURL)
            }
        }
        composable(route = NavigationScreens.SettingScreen.route) {
            sharedViewModel.updatesSearchViewState(SharedViewModel.SearchViewState.SEARCH_CLOSED)
            SettingScreen()
        }
    }
}

private fun openWebViewActivity(activity: Activity, url: String) {
    WebViewActivity.startWebViewActivity(activity, Bundle().apply {
        putString(WebViewActivity.ProfileURL, url)
    })
}