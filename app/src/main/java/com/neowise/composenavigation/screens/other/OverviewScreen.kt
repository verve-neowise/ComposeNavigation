package com.neowise.composenavigation.screens.other

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.neowise.composenavigation.model.User
import com.neowise.composenavigation.screens.util.NavArguments
import com.neowise.composenavigation.screens.util.None
import com.neowise.composenavigation.screens.util.Route

class OverviewRoute(arguments: Arguments) : Route<OverviewRoute.Arguments>(path, arguments) {
    companion object {
        const val path = "overview"

        fun withArguments(user: User): Route<Arguments> {
            return OverviewRoute(Arguments(user))
        }
    }

    class Arguments(val user: User) : NavArguments
}

@Composable
fun OverviewScreen(user: User?) {
    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "User overview",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        if (user != null) {
            Text(
                modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
                text = "Name: ${user.name}\nSurname: ${user.surname}\nAge: ${user.age}",
                fontSize = 14.sp,
            )
        }
        else {
            Text(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                text = "No user selected",
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal
            )
        }
    }
}