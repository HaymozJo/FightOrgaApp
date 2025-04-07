package com.example.fightorgaapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fightorgaapp.data.model.Fighter
import com.example.fightorgaapp.data.repository.FighterRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class FighterUiState(
    val fighters: List<Fighter> = emptyList()
)

class FighterViewModel(
    private val fighterRepository: FighterRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(FighterUiState())
    val uiState: StateFlow<FighterUiState> = _uiState.asStateFlow()

    init {
        loadFighters()
    }

    private fun loadFighters() {
        viewModelScope.launch {
            fighterRepository.getAllFighters().collect { fighters ->
                _uiState.value = _uiState.value.copy(fighters = fighters)
            }
        }
    }

    fun addFighter(name: String) {
        viewModelScope.launch {
            val fighter = Fighter(name = name)
            fighterRepository.insertFighter(fighter)
        }
    }

    fun deleteFighter(fighter: Fighter) {
        viewModelScope.launch {
            fighterRepository.deleteFighter(fighter)
        }
    }
} 