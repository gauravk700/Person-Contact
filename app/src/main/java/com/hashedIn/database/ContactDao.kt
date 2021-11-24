package com.hashedIn.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import org.jetbrains.annotations.NotNull
import org.jetbrains.annotations.Nullable

@Dao
interface ContactDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllContact(vararg contactHasher: ContactHasher)

    @Query("SELECT * FROM hasherContact")
    fun getAllContacts(): LiveData<List<ContactHasher>>
}