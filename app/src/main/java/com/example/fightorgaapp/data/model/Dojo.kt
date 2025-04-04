package com.example.fightorgaapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dojos")
data class Dojo(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val address: String,
    val coachIds: List<Long> = emptyList(),
    val fighterIds: List<Long> = emptyList()
) 