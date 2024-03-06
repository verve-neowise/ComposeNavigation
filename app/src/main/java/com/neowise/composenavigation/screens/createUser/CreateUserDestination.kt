package com.neowise.composenavigation.screens.createUser

import androidx.compose.runtime.Composable
import com.neowise.composenavigation.model.UserDto
import com.neowise.composenavigation.screens.util.NavResult
import com.neowise.composenavigation.screens.util.None
import com.neowise.composenavigation.screens.util.Route

class CreateUserRoute : Route<None>(path) {
    companion object {
        const val path = "createUser"
    }

    class Result(val user: UserDto): NavResult
}

@Composable
fun CreateUserDestination(
    onUserCreated: (UserDto) -> Unit,
    onNavigateBack: () -> Unit
) {
    CreateUserScreen(
        onUserCreated = onUserCreated,
        onNavigateBack = onNavigateBack
    )
}