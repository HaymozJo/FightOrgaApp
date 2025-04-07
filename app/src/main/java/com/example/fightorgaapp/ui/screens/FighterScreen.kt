package com.example.fightorgaapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fightorgaapp.data.model.Fighter
import com.example.fightorgaapp.ui.viewmodel.FighterViewModel

@Composable
fun FighterScreen(viewModel: FighterViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    var newFighterName by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Add Fighter Section
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = newFighterName,
                onValueChange = { newFighterName = it },
                label = { Text("Fighter Name") },
                modifier = Modifier.weight(1f)
            )
            
            Spacer(modifier = Modifier.width(8.dp))
            
            Button(
                onClick = {
                    if (newFighterName.isNotBlank()) {
                        viewModel.addFighter(newFighterName)
                        newFighterName = "" // Clear the input
                    }
                }
            ) {
                Text("Add")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Fighters List
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(uiState.fighters) { fighter ->
                FighterItem(
                    fighter = fighter,
                    onDelete = { viewModel.deleteFighter(fighter) }
                )
            }
        }
    }
}

@Composable
private fun FighterItem(
    fighter: Fighter,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(fighter.name)
            IconButton(onClick = onDelete) {
                Text("🗑️") // Simple delete icon
            }
        }
    }
} 