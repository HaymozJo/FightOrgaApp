package com.example.fightorgaapp

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fightorgaapp.ui.navigation.NavGraph
import com.example.fightorgaapp.ui.navigation.Screen
import com.example.fightorgaapp.ui.screens.FighterScreen
import com.example.fightorgaapp.ui.screens.home.HomeScreen
import com.example.fightorgaapp.ui.viewmodel.FighterViewModel

@Composable
fun FightOrgaApp(fighterViewModel: FighterViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        route = NavGraph.Main.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen(navController)
        }
        composable(Screen.Fighters.route) {
            FighterScreen(fighterViewModel)
        }
        composable(Screen.Teams.route) {
            // TODO: Implement TeamsScreen
        }
        composable(Screen.Fights.route) {
            // TODO: Implement FightsScreen
        }
    }
} 