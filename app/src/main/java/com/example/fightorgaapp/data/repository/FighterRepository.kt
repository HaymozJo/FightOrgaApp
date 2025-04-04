package com.example.fightorgaapp.data.repository

import com.example.fightorgaapp.data.database.FighterDao
import com.example.fightorgaapp.data.database.FightDao
import com.example.fightorgaapp.data.model.Fighter
import com.example.fightorgaapp.data.model.shared.FighterStatistics
import kotlinx.coroutines.flow.Flow

class FighterRepository(
    private val fighterDao: FighterDao,
    private val fightDao: FightDao
) {
    
    // Get all fighters as a Flow
    fun getAllFighters(): Flow<List<Fighter>> = fighterDao.getAllFighters()

    // Get a specific fighter
    suspend fun getFighterById(id: Long): Fighter? = fighterDao.getFighterById(id)

    // Insert a new fighter
    suspend fun insertFighter(fighter: Fighter): Long = fighterDao.insertFighter(fighter)

    // Update an existing fighter
    suspend fun updateFighter(fighter: Fighter) = fighterDao.updateFighter(fighter)

    // Delete a fighter
    suspend fun deleteFighter(fighter: Fighter) = fighterDao.deleteFighter(fighter)

    // Get fighters by team
    fun getFightersByTeam(teamId: Long): Flow<List<Fighter>> = 
        fighterDao.getFightersByTeam(teamId)

    // Enhanced fighter operations with fight statistics
    suspend fun getFighterWithStatistics(fighterId: Long): FighterWithStatistics? {
        val fighter = getFighterById(fighterId) ?: return null
        val statistics = fightDao.getFighterStatistics(fighterId)
        return FighterWithStatistics(fighter, statistics)
    }

    // Business logic example using both DAOs
    suspend fun canDeleteFighter(fighterId: Long): Boolean {
        val fighter = getFighterById(fighterId) ?: return false
        val statistics = fightDao.getFighterStatistics(fighterId)
        return statistics.totalFights == 0
    }

    //Update fighter's real weight
    suspend fun updateFighterRealWeight(fighterId: Long, newWeight: Float): Boolean {
        val fighter = getFighterById(fighterId) ?: return false
        val updatedFighter = fighter.copy(realWeight = newWeight)
        updateFighter(updatedFighter)
        return true
    }

    //Update fighter's announced weight
    suspend fun updateFighterAnnouncedWeight(fighterId: Long, newWeight: Float): Boolean {
        val fighter = getFighterById(fighterId) ?: return false
        val updatedFighter = fighter.copy(announcedWeight = newWeight)
        updateFighter(updatedFighter)
        return true
    }

    // Business logic example: Check if a fighter can be assigned to a team
    suspend fun canAssignToTeam(fighterId: Long, teamId: Long): Boolean {
        val fighter = getFighterById(fighterId) ?: return false
        // Add any business rules here
        return true
    }
}

data class FighterWithStatistics(
    val fighter: Fighter,
    val statistics: FighterStatistics
) 