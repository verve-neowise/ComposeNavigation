# Compose Navigation

Compose Navigation extensions with arguments using `savedStateHandle`

## How to use

### Define route

without arguments

```kotlin
class CreateUserRoute : Route<None>(path) {
    companion object {
        const val path = "createUser"
    }
}
```

With arguments

> Recommend defining `withArguments` function inside companion for more readability

```kotlin
class OverviewRoute(arguments: Arguments) : Route<OverviewRoute.Arguments>(path, arguments) {
    companion object {
        const val path = "overview"

        fun withArguments(user: User): Route<Arguments> {
            return OverviewRoute(Arguments(user))
        }
    }

    class Arguments(val user: User) : NavArguments
}
```

### Navigate

Without arguments using `navigateTo` function

``` kotlin
navController.navigateTo(CreateUserRoute())
```

With arguments

```kotlin
navController.navigateTo(OverviewRoute.withArguments(user))
```

### Return result from screen

Define Route

```kotlin
class CreateUserRoute : Route<None>(path) {
    companion object {
        const val path = "createUser"
    }
    class Result(val user: UserDto): NavResult
}
```

Back to preview screen with result

```kotlin
Button(
    onClick = { navController.backWithResult(CreateUserRoute.Result(it)) }
) {
    Text("Create User")
}
```

Observe result from first screen

```kotlin
val result = navController.result<CreateUserRoute.Result>()

LaunchedEffect(Unit) {
    if (result.user != null) {
        someAction(result.user)
    }
}
```