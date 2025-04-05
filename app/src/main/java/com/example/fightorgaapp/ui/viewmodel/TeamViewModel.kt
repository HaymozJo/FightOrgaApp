package com.example.fightorgaapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fightorgaapp.data.model.Team
import com.example.fightorgaapp.data.repository.TeamRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

// Simple state containing just the list of teams
data class TeamUiState(
    val teams: List<Team> = emptyList()
)

class TeamViewModel(
    private val teamRepository: TeamRepository
) : ViewModel() {

    // State management
    private val _uiState = MutableStateFlow(TeamUiState())
    val uiState: StateFlow<TeamUiState> = _uiState.asStateFlow()

    init {
        loadTeams()
    }

    // Load all teams
    private fun loadTeams() {
        viewModelScope.launch {
            teamRepository.getAllTeams().collect { teams ->
                _uiState.update { it.copy(teams = teams) }
            }
        }
    }

    // Add a new team
    fun addTeam(team: Team) {
        viewModelScope.launch {
            teamRepository.insertTeam(team)
        }
    }

    // Update an existing team
    fun updateTeam(team: Team) {
        viewModelScope.launch {
            teamRepository.updateTeam(team)
        }
    }

    // Delete a team
    fun deleteTeam(team: Team) {
        viewModelScope.launch {
            teamRepository.deleteTeam(team)
        }
    }
} 