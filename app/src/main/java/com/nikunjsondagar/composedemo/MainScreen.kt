package com.nikunjsondagar.composedemo

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        topBar = { MainScreenTopBar() },
        bottomBar = { BottomBar(navHostController = navController) }) { innerPadding ->
        MainScreenNavigationGraph(navHostController = navController, innerPadding)
    }
}

@Composable
fun MainScreenTopBar() {
    SmallTopAppBar(title = {
        Text(text = stringResource(id = R.string.app_name))
    })
}

@Composable
fun BottomBar(navHostController: NavHostController) {
    val screens = listOf(
        NavigationScreens.UserScreen, NavigationScreens.RepoScreen, NavigationScreens.SettingScreen
    )

    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    NavigationBar {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navHostController = navHostController
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: NavigationScreens,
    currentDestination: NavDestination?,
    navHostController: NavHostController
) {
    NavigationBarItem(selected = currentDestination?.hierarchy?.any {
        it.route == screen.route
    } == true, label = {
        Text(text = screen.title)
    }, icon = {
        Image(imageVector = screen.icon, contentDescription = screen.title)
    }, onClick = {
        navHostController.navigate(screen.route)
    })
}

