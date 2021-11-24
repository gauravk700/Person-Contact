package com.hashedIn.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ContactHasher::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun contactDao(): ContactDao
}