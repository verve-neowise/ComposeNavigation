package com.neowise.composenavigation.model

data class User(
    val id: String,
    val name: String,
    val surname: String,
    val age: String
)

fun User.toDto() = UserDto(name, surname, age)