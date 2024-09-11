package com.example.netplix.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.netplix.ui.navigation.Destinations.MOVIE_HOME_ROUTE
import com.example.netplix.ui.navigation.Destinations.MOVIE_SEARCH_ROUTE
import com.example.netplix.ui.screens.home.HomeScreen
import com.example.netplix.ui.screens.search.SearchScreen

@Composable
fun NavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = MOVIE_HOME_ROUTE
    ) {
        composable(MOVIE_HOME_ROUTE) {
           HomeScreen(
               navController = navController
           )
        }
        composable("${MOVIE_SEARCH_ROUTE}/{movieName}") {
            val movieName = it.arguments?.getString("movieName") ?: ""
            SearchScreen(navController = navController, movieName = movieName)
        }
    }
}

object Destinations {
    const val MOVIE_HOME_ROUTE = "movie_home_route"
    const val MOVIE_SEARCH_ROUTE = "movie_search_route"
}