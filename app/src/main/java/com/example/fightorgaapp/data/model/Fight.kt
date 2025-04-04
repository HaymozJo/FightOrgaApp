package com.example.fightorgaapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

// Base abstract class for all fights
@Entity(tableName = "fights")
abstract class Fight(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val type: FightType,
    val fighter1Id: Long,
    val fighter2Id: Long,
    val result: FightResult? = null,
    val timer: Long = 0 // in milliseconds
)

// JJB specific fight with advantages
@Entity(tableName = "jjb_fights")
data class JJBFight(
    override val id: Long = 0,
    override val fighter1Id: Long,
    override val fighter2Id: Long,
    override val result: FightResult? = null,
    override val timer: Long = 0,
    val fighter1Advantages: Int = 0,
    val fighter2Advantages: Int = 0,
    val fighter1Points: Int = 0,
    val fighter2Points: Int = 0
) : Fight(id, FightType.JJB, fighter1Id, fighter2Id, result, timer)

// Boxing specific fight with rounds
@Entity(tableName = "boxing_fights")
data class BoxingFight(
    override val id: Long = 0,
    override val fighter1Id: Long,
    override val fighter2Id: Long,
    override val result: FightResult? = null,
    override val timer: Long = 0,
    val rounds: Int = 3,
    val fighter1RoundWins: Int = 0,
    val fighter2RoundWins: Int = 0
) : Fight(id, FightType.BOXING, fighter1Id, fighter2Id, result, timer)

enum class FightResult {
    FIGHTER1_WIN,
    FIGHTER2_WIN,
    DRAW
} 