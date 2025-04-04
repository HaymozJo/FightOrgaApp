package com.example.fightorgaapp.data.repository

import com.example.fightorgaapp.data.database.TeamDao
import com.example.fightorgaapp.data.model.Team
import kotlinx.coroutines.flow.Flow

class TeamRepository(
    private val teamDao: TeamDao,
) {
    
    // Get all Teams as a Flow
    fun getAllTeams(): Flow<List<Team>> = teamDao.getAllTeams()

    // Get a specific team
    suspend fun getTeamById(id: Long): Team? = teamDao.getTeamById(id)

    // Insert a new team
    suspend fun insertTeam(team: Team): Long = teamDao.insertTeam(team)

    // Update an existing team
    suspend fun updateTeam(team: Team) = teamDao.updateTeam(team)

    // Delete a team
    suspend fun deleteTeam(team: Team) = teamDao.deleteTeam(team)

}