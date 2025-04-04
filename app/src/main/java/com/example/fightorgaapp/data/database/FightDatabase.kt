package com.example.fightorgaapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.fightorgaapp.data.model.Fighter
import com.example.fightorgaapp.data.model.JJBFight

@Database(
    entities = [Fighter::class, JJBFight::class],
    version = 1,
    exportSchema = false
)
abstract class FightDatabase : RoomDatabase() {
    abstract fun fighterDao(): FighterDao
    abstract fun fightDao(): FightDao
} 