package com.example.fightorgaapp.data.repository

import com.example.fightorgaapp.data.database.FightDao
import com.example.fightorgaapp.data.model.JJBFight
import com.example.fightorgaapp.data.model.shared.FighterStatistics
import kotlinx.coroutines.flow.Flow

class FightRepository(private val fightDao: FightDao) {
    
    // Get all fights as a Flow
    fun getAllFights(): Flow<List<JJBFight>> = fightDao.getAllFights()

    // Get a specific fight
    suspend fun getFightById(id: Long): JJBFight? = fightDao.getFightById(id)

    // Insert a new fight
    suspend fun insertFight(fight: JJBFight): Long = fightDao.insertFight(fight)

    // Update an existing fight
    suspend fun updateFight(fight: JJBFight) = fightDao.updateFight(fight)

    // Delete a fight
    suspend fun deleteFight(fight: JJBFight) = fightDao.deleteFight(fight)

    // Update a fight Score
    suspend fun updateFightScore(fightId: Long, scoreFighter1: Int, scoreFighter2: Int): Boolean {
        val fight = getFightById(fightId) ?: return false
        val updatedFight = fight.copy(
            fighter1Points = scoreFighter1,
            fighter2Points = scoreFighter2
        )
        updateFight(updatedFight)
        return true
    }

    // Get fighter statistics
    suspend fun getFighterStatistics(fighterId: Long): FighterStatistics {
        val fights = getAllFights().first()
        return FighterStatistics.calculate(fights, fighterId)
    }
} 