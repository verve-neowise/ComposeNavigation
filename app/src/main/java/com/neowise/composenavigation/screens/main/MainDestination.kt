package com.neowise.composenavigation.screens.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import com.neowise.composenavigation.model.User
import com.neowise.composenavigation.model.UserDto
import com.neowise.composenavigation.screens.util.None
import com.neowise.composenavigation.screens.util.Route

class MainRoute : Route<None>(path) {
    companion object {
        const val path = "main"
    }
}

@Composable
fun MainDestination(
    createdUser: UserDto? = null,
    navigateToCreateUser: () -> Unit,
    navigateToOverview: (User) -> Unit
) {
    val vm: MainViewModel = viewModel()

    LaunchedEffect(Unit) {
        if (createdUser != null) {
            vm.addUser(createdUser)
        }
    }

    MainScreen(
        users = vm.state.users,
        input = vm.state.input,
        inputChanged = vm::inputChanged,
        navigateToCreateUser = navigateToCreateUser,
        navigateToOverview = navigateToOverview
    )
}

