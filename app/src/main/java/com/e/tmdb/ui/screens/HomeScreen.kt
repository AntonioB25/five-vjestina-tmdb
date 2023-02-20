package com.e.tmdb.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.e.tmdb.R
import com.e.tmdb.ui.bottomNav.BottomNavItem
import com.e.tmdb.ui.bottomNav.FavouritesScreen
import com.e.tmdb.ui.bottomNav.Home
import com.e.tmdb.ui.theme.DarkBlue


@Composable
fun HomeScreen(
    navigateToDetails: (Int) -> Unit,
) {
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            Box(
                Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(DarkBlue),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "App logo",
                    Modifier
                        .height(34.dp)
                        .width(136.dp)
                )
            }
        },
        bottomBar = { BottomNavigation(navController = navController) }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            HomeNavigationGraph(
                navController = navController,
                navigateToDetails = navigateToDetails,
            )
        }
    }
}


@Composable
fun BottomNavigation(navController: NavController) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Favourites
    )

    androidx.compose.material.BottomNavigation(
        contentColor = Color.Green,
        backgroundColor = Color.White,
        elevation = 10.dp
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { screen ->
            BottomNavigationItem(
                selected = currentRoute == screen.screen_route,
                selectedContentColor = DarkBlue,
                unselectedContentColor = DarkBlue.copy(0.4f),
                icon = {
                    Icon(
                        imageVector = if (currentRoute == screen.screen_route) screen.selectedIcon else screen.unselectedIcon,
                        contentDescription = screen.title
                    )
                },
                label = {
                    Text(
                        text = screen.title,
                        fontSize = 8.sp
                    )
                },
                alwaysShowLabel = true,
                onClick = {
                    navController.navigate(screen.screen_route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}


@Composable
fun HomeNavigationGraph(
    navController: NavHostController,
    navigateToDetails: (Int) -> Unit,
) {
    NavHost(navController, startDestination = BottomNavItem.Home.screen_route) {
        composable(BottomNavItem.Home.screen_route) {
            Home(navigateToDetails)
        }
        composable(BottomNavItem.Favourites.screen_route) {
            FavouritesScreen(navigateToDetails)
        }
    }
}