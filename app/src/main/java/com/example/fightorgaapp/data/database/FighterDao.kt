package com.example.fightorgaapp.data.database

import androidx.room.*
import com.example.fightorgaapp.data.model.Fighter
import kotlinx.coroutines.flow.Flow

@Dao
interface FighterDao {
    @Query("SELECT * FROM fighters")
    fun getAllFighters(): Flow<List<Fighter>>

    @Query("SELECT * FROM fighters WHERE id = :id")
    suspend fun getFighterById(id: Long): Fighter?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFighter(fighter: Fighter): Long

    @Update
    suspend fun updateFighter(fighter: Fighter)

    @Delete
    suspend fun deleteFighter(fighter: Fighter)

    @Query("SELECT * FROM fighters WHERE dojoId = :dojoId")
    fun getFightersByDojo(dojoId: Long): Flow<List<Fighter>>
} 