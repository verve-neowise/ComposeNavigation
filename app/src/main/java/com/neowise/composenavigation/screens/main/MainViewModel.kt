package com.neowise.composenavigation.screens.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.neowise.composenavigation.model.User
import com.neowise.composenavigation.model.UserDto
import java.util.UUID

data class State(
    val users: List<User> = listOf(),
    val input: String = ""
)

class MainViewModel : ViewModel() {

    var state by mutableStateOf(State())

    fun addUser(userDto: UserDto) {
        val user = User(
            id = UUID.randomUUID().toString(),
            name = userDto.name,
            surname = userDto.surname,
            age = userDto.age
        )
        state = state.copy(
            users = state.users + user
        )
    }

    fun inputChanged(value: String) {
        state = state.copy(input = value)
    }
}