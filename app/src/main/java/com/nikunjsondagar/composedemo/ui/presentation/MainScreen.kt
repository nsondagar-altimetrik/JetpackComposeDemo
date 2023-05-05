package com.nikunjsondagar.composedemo.ui.presentation

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.nikunjsondagar.composedemo.navigation.MainScreenNavigationGraph
import com.nikunjsondagar.composedemo.navigation.NavigationScreens
import com.nikunjsondagar.composedemo.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(activity: Activity) {
    val navController = rememberNavController()
    val sharedViewModel = hiltViewModel<SharedViewModel>()
    Scaffold(
        topBar = { MainScreenBar(sharedViewModel) },
        bottomBar = { BottomBar(navHostController = navController) }) { innerPadding ->
        MainScreenNavigationGraph(
            navHostController = navController,
            innerPadding,
            activity,
            sharedViewModel
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MainScreenBar(sharedViewModel: SharedViewModel) {
    when (sharedViewModel.searchViewState.value) {
        SharedViewModel.SearchViewState.SEARCH_ACTIVE -> {
            val keyboardController = LocalSoftwareKeyboardController.current
            SearchUsersTopBar(
                searchText = sharedViewModel.searchText.value,
                searchTextPlaceHolder = stringResource(id = R.string.search_text_placeholder),
                onSearchTextChange = {
                    sharedViewModel.updateSearchText(it)
                },
                onSearchClicked = {
                    sharedViewModel.clickButton(true)
                    keyboardController?.hide()
                },
                onSearchCloseClicked = {
                    sharedViewModel.updateSearchText("")
                    sharedViewModel.updatesSearchViewState(SharedViewModel.SearchViewState.SEARCH_CLOSED)
                }
            )
        }

        SharedViewModel.SearchViewState.SEARCH_CLOSED -> {
            MainScreenTopBar(
                isInternetAvailable = sharedViewModel.isInternetAvailable(),
                onSearchClicked = {
                    sharedViewModel.updatesSearchViewState(SharedViewModel.SearchViewState.SEARCH_ACTIVE)
                })
        }
    }
}

@Composable
fun MainScreenTopBar(onSearchClicked: () -> Unit, isInternetAvailable: Boolean) {
    CenterAlignedTopAppBar(title = {
        Text(
            text = stringResource(id = R.string.app_name)
        )
    }, actions = {
        if (isInternetAvailable) {
            IconButton(onClick = { onSearchClicked() }) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search icon",
                    tint = Color.DarkGray
                )
            }
        }
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

