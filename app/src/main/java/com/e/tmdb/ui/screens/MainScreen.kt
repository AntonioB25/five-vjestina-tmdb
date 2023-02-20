package com.e.tmdb.ui.screens

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.e.tmdb.ui.bottomNav.BottomNavItem


@Composable
fun MainScreen(
    navController: NavHostController,
    navigateToDetails: (Int?) -> Unit,
) {

    Scaffold() {
        MainNavigationGraph(
            navController = navController,
            navigateToDetails = navigateToDetails,
        )
    }

}


@Composable
fun MainNavigationGraph(
    navController: NavHostController,
    navigateToDetails: (Int) -> Unit,
) {
    NavHost(navController, startDestination = BottomNavItem.Home.screen_route) {
        composable(BottomNavItem.Home.screen_route) {
            HomeScreen(navigateToDetails)
        }

        composable(
            "details/{movieId}",
            arguments = listOf(navArgument("movieId") { type = NavType.IntType })
        ) { navBackStackEntry ->
            DetailsScreen(movieId = navBackStackEntry.arguments?.getInt("movieId")!!)
        }
    }
}
