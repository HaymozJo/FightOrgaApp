package com.example.fightorgaapp.data.database

import androidx.room.*
import com.example.fightorgaapp.data.model.JJBFight
import kotlinx.coroutines.flow.Flow

@Dao
interface FightDao {
    @Query("SELECT * FROM jjb_fights")
    fun getAllFights(): Flow<List<JJBFight>>

    @Query("SELECT * FROM jjb_fights WHERE id = :id")
    suspend fun getFightById(id: Long): JJBFight?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFight(fight: JJBFight): Long

    @Update
    suspend fun updateFight(fight: JJBFight)

    @Delete
    suspend fun deleteFight(fight: JJBFight)

    // Score-related queries
    @Query("""
        SELECT SUM(fighter1Points) as totalPoints 
        FROM jjb_fights 
        WHERE fighter1Id = :fighterId
    """)
    suspend fun getTotalPointsAsFighter1(fighterId: Long): Int

    @Query("""
        SELECT SUM(fighter2Points) as totalPoints 
        FROM jjb_fights 
        WHERE fighter2Id = :fighterId
    """)
    suspend fun getTotalPointsAsFighter2(fighterId: Long): Int

    @Query("""
        SELECT COUNT(*) 
        FROM jjb_fights 
        WHERE (fighter1Id = :fighterId AND result = 'FIGHTER1_WIN') 
           OR (fighter2Id = :fighterId AND result = 'FIGHTER2_WIN')
    """)
    suspend fun getTotalWins(fighterId: Long): Int
}