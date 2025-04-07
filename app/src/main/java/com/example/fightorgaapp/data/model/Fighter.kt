package com.example.fightorgaapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fighters")
data class Fighter(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String
) 