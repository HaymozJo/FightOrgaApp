package com.example.fightorgaapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fightorgaapp.data.model.JJBFight
import com.example.fightorgaapp.data.repository.FightRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

// Simple state containing just the list of fights
data class FightUiState(
    val fights: List<JJBFight> = emptyList()
)

class FightViewModel(
    private val fightRepository: FightRepository
) : ViewModel() {

    // State management
    private val _uiState = MutableStateFlow(FightUiState())
    val uiState: StateFlow<FightUiState> = _uiState.asStateFlow()

    init {
        loadFights()
    }

    // Load all fights
    private fun loadFights() {
        viewModelScope.launch {
            fightRepository.getAllFights().collect { fights ->
                _uiState.update { it.copy(fights = fights) }
            }
        }
    }

    // Add a new fight
    fun addFight(fight: JJBFight) {
        viewModelScope.launch {
            fightRepository.insertFight(fight)
        }
    }

    // Update an existing fight
    fun updateFight(fight: JJBFight) {
        viewModelScope.launch {
            fightRepository.updateFight(fight)
        }
    }

    // Delete a fight
    fun deleteFight(fight: JJBFight) {
        viewModelScope.launch {
            fightRepository.deleteFight(fight)
        }
    }

    // Update fight score
    fun updateFightScore(fightId: Long, scoreFighter1: Int, scoreFighter2: Int) {
        viewModelScope.launch {
            fightRepository.updateFightScore(fightId, scoreFighter1, scoreFighter2)
        }
    }
} 