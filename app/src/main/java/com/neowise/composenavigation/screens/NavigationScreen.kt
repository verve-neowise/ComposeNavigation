package com.neowise.composenavigation.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.neowise.composenavigation.screens.createUser.CreateUserDestination
import com.neowise.composenavigation.screens.createUser.CreateUserRoute
import com.neowise.composenavigation.screens.main.MainDestination
import com.neowise.composenavigation.screens.main.MainRoute
import com.neowise.composenavigation.screens.other.OverviewScreen
import com.neowise.composenavigation.screens.other.OverviewRoute
import com.neowise.composenavigation.screens.util.arguments
import com.neowise.composenavigation.screens.util.navigateBack
import com.neowise.composenavigation.screens.util.backWithResult
import com.neowise.composenavigation.screens.util.navigateTo
import com.neowise.composenavigation.screens.util.result

@Composable
fun NavigationScreen() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = MainRoute.path
    ) {
        composable(MainRoute.path) { _ ->
            val result = navController.result<CreateUserRoute.Result>()
            MainDestination(
                createdUser = result?.user,
                navigateToCreateUser = {
                    navController.navigateTo(CreateUserRoute())
                },
                navigateToOverview = { user ->
                    navController.navigateTo(OverviewRoute.withArguments(user))
                }
            )
        }
        composable(CreateUserRoute.path) {
            CreateUserDestination(
                onUserCreated = {
                    navController.backWithResult(CreateUserRoute.Result(it))
                },
                onNavigateBack = {
                    navController.navigateBack()
                }
            )
        }
        composable(OverviewRoute.path) {
            val arguments = remember { navController.arguments<OverviewRoute.Arguments>() }
            OverviewScreen(arguments?.user)
        }
    }
}