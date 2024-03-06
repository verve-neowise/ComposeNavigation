package com.neowise.composenavigation.screens.util

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.google.gson.Gson

const val ARGUMENTS = "_arguments"
const val RESULT = "_result"

interface NavArguments
interface NavResult

object None : NavArguments

open class Route<T : NavArguments>(val path: String, val arguments: T? = null)

fun NavHostController.navigateBack() {
    popBackStack()
}

fun <T: NavResult> NavHostController.backWithResult(result: T? = null) {
    if (result != null) {
        previousBackStackEntry?.savedStateHandle?.set(RESULT, Gson().toJson(result))
    }
    popBackStack()
}

@Composable
inline fun <reified T : NavResult> NavController.result(): T? {
    val json = currentBackStackEntry?.savedStateHandle?.get<String>(RESULT)
    currentBackStackEntry?.savedStateHandle?.remove<String>(RESULT)
    return json?.let { Gson().fromJson(json, T::class.java) }
}

inline fun <reified T : NavArguments> NavController.arguments(): T? {
    val json = previousBackStackEntry?.savedStateHandle?.get<String>(ARGUMENTS)
    return json?.let { Gson().fromJson(json, T::class.java) }
}

inline fun <reified T : NavArguments> NavController.navigateTo(route: Route<T>) {
    currentBackStackEntry?.savedStateHandle?.remove<String>(ARGUMENTS)
    if (route.arguments != null) {
        currentBackStackEntry?.savedStateHandle?.set(ARGUMENTS, Gson().toJson(route.arguments))
    }
    navigate(route.path)
}