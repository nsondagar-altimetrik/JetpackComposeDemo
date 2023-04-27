package com.nikunjsondagar.composedemo

import android.app.Activity
import android.os.Bundle
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nikunjsondagar.feature_repos.RepositoriesScreen
import com.nikunjsondagar.feature_settings.SettingScreen
import com.nikunjsondagar.feature_users.presentation.DisplayUsersList
import com.nikunjsondagar.feature_users.presentation.UserListViewModel

@Composable
fun MainScreenNavigationGraph(
    navHostController: NavHostController,
    padding: PaddingValues,
    activity: Activity
) {
    NavHost(
        navController = navHostController,
        startDestination = NavigationScreens.UserScreen.route,
        modifier = Modifier.padding(padding)
    ) {
        composable(route = NavigationScreens.UserScreen.route) {
            val userListViewModel = hiltViewModel<UserListViewModel>()
            val state by userListViewModel.state.collectAsState()
            DisplayUsersList(state) { profileURL ->
                openWebViewActivity(activity, profileURL)
            }
        }
        composable(route = NavigationScreens.RepoScreen.route) {
            RepositoriesScreen()
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