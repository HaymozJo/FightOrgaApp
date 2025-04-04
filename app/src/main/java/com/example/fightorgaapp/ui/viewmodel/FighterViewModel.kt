package com.example.fightorgaapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fightorgaapp.data.model.Fighter
import com.example.fightorgaapp.data.repository.FighterRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

// Simple state containing just the list of fighters
data class FighterUiState(
    val fighters: List<Fighter> = emptyList()
)

class FighterViewModel(
    private val fighterRepository: FighterRepository
) : ViewModel() {

    // State management
    private val _uiState = MutableStateFlow(FighterUiState())
    val uiState: StateFlow<FighterUiState> = _uiState.asStateFlow()

    init {
        loadFighters()
    }

    // Load all fighters
    private fun loadFighters() {
        viewModelScope.launch {
            fighterRepository.getAllFighters().collect { fighters ->
                _uiState.update { it.copy(fighters = fighters) }
            }
        }
    }

    // Add a new fighter
    fun addFighter(fighter: Fighter) {
        viewModelScope.launch {
            fighterRepository.insertFighter(fighter)
        }
    }

    // Update an existing fighter
    fun updateFighter(fighter: Fighter) {
        viewModelScope.launch {
            fighterRepository.updateFighter(fighter)
        }
    }

    // Delete a fighter
    fun deleteFighter(fighter: Fighter) {
        viewModelScope.launch {
            fighterRepository.deleteFighter(fighter)
        }
    }
} 