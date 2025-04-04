package com.example.fightorgaapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fighters")
data class Fighter(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val isCoach: Boolean,
    val dojoId: Long?,
    val announcedWeight: Float,
    val realWeight: Float?,
    val notFightingWith: List<Long> = emptyList(),
    val fightTypes: List<FightType> = listOf(FightType.JJB)
) 