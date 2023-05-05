package com.nikunjsondagar.composedemo.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavigationScreens(val route: String, val title: String, val icon: ImageVector) {
    object UserScreen : NavigationScreens("user", "Users", Icons.Default.Person)
    object RepoScreen : NavigationScreens("repos", "Repos", Icons.Default.List)
    object SettingScreen : NavigationScreens("settings", "Settings", Icons.Default.Settings)
}
