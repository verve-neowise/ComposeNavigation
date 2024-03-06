package com.neowise.composenavigation.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.neowise.composenavigation.model.User

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    input: String,
    users: List<User> = listOf(),
    inputChanged: (String) -> Unit,
    navigateToCreateUser: () -> Unit,
    navigateToOverview: (User) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Users") },
                actions = {
                    IconButton(onClick = navigateToCreateUser) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = "")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            TextField(
                modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp),
                value = input,
                onValueChange = inputChanged
            )

            users.forEach { user ->
                UserInfo(
                    user = user,
                    onClick = {
                      navigateToOverview(user)
                    }
                )
            }
        }
    }
}

@Composable
private fun UserInfo(
    onClick: () -> Unit,
    user: User
) {
    Column(
        Modifier
            .fillMaxWidth()
            .clickable(
                onClick = onClick
            )
            .clip(RoundedCornerShape(4.dp))
            .background(Color(0xFFF9FAFB))
            .padding(8.dp)
    ) {
        Text(
            text = "${user.name} ${user.surname}",
            fontSize = 16.sp
        )
        Text(
            text = "${user.age} y.o.",
            fontSize = 13.sp
        )
    }
}