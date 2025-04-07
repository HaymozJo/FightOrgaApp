package com.example.fightorgaapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.example.fightorgaapp.data.database.FightDatabase
import com.example.fightorgaapp.data.repository.FighterRepository
import com.example.fightorgaapp.ui.theme.FightOrgaAppTheme
import com.example.fightorgaapp.ui.viewmodel.FighterViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Create database and repository
        val database = FightDatabase.getDatabase(this)
        val repository = FighterRepository(database.fighterDao())
        
        // Create ViewModel
        val viewModel = ViewModelProvider(
            this,
            object : ViewModelProvider.Factory {
                override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
                    return FighterViewModel(repository) as T
                }
            }
        )[FighterViewModel::class.java]

        setContent {
            FightOrgaAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FightOrgaApp(viewModel)
                }
            }
        }
    }
} 