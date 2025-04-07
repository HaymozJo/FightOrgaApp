package com.example.fightorgaapp.data.database

import androidx.room.*
import com.example.fightorgaapp.data.model.Fighter
import kotlinx.coroutines.flow.Flow

@Dao
interface FighterDao {
    @Query("SELECT * FROM fighters")
    fun getAllFighters(): Flow<List<Fighter>>

    @Insert
    suspend fun insertFighter(fighter: Fighter)

    @Delete
    suspend fun deleteFighter(fighter: Fighter)
} 