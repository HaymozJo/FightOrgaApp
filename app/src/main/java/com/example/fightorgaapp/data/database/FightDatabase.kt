package com.example.fightorgaapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.fightorgaapp.data.model.Fighter
import com.example.fightorgaapp.data.model.JJBFight
import com.example.fightorgaapp.data.model.Team

@Database(
    entities = [Fighter::class, JJBFight::class, Team::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class FightDatabase : RoomDatabase() {
    abstract fun fighterDao(): FighterDao
    abstract fun fightDao(): FightDao
    abstract fun teamDao(): TeamDao
} 