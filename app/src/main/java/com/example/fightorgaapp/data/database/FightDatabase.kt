package com.example.fightorgaapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.fightorgaapp.data.model.Fighter

@Database(
    entities = [Fighter::class],
    version = 1,
    exportSchema = false
)
abstract class FightDatabase : RoomDatabase() {
    abstract fun fighterDao(): FighterDao

    companion object {
        @Volatile
        private var INSTANCE: FightDatabase? = null 

        fun getDatabase(context: Context): FightDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FightDatabase::class.java,
                    "fight_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
} 