package com.nikunjsondagar.composedemo

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nikunjsondagar.feature_repos.RepositoriesScreen
import com.nikunjsondagar.feature_settings.SettingScreen
import com.nikunjsondagar.feature_users.UserScreen

@Composable
fun MainScreenNavigationGraph(navHostController: NavHostController, padding : PaddingValues) {
    NavHost(
        navController = navHostController,
        startDestination = NavigationScreens.UserScreen.route,
        modifier = Modifier.padding(padding)
    ) {
        composable(route = NavigationScreens.UserScreen.route) {
            UserScreen()
        }
        composable(route = NavigationScreens.RepoScreen.route) {
            RepositoriesScreen()
        }
        composable(route = NavigationScreens.SettingScreen.route) {
            SettingScreen()
        }
    }
}