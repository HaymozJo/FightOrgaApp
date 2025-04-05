package com.example.fightorgaapp.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Fighters : Screen("fighters")
    object Teams : Screen("teams")
    object Fights : Screen("fights")
}

sealed class NavGraph(val route: String) {
    object Main : NavGraph("main")
} 