package com.example.fightorgaapp.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Fighters : Screen("fighters")
    object Teams : Screen("teams")
    object Fights : Screen("fights")
} 