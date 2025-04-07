package com.example.fightorgaapp.data.repository

import com.example.fightorgaapp.data.database.FighterDao
import com.example.fightorgaapp.data.model.Fighter
import kotlinx.coroutines.flow.Flow

class FighterRepository(private val fighterDao: FighterDao) {
    fun getAllFighters(): Flow<List<Fighter>> = fighterDao.getAllFighters()

    suspend fun insertFighter(fighter: Fighter) = fighterDao.insertFighter(fighter)

    suspend fun deleteFighter(fighter: Fighter) = fighterDao.deleteFighter(fighter)
} 