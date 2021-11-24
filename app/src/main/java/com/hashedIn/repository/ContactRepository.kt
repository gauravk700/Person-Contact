package com.hashedIn.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.hashedIn.database.ContactDao
import com.hashedIn.database.ContactHasher
import kotlinx.coroutines.*


class ContactRepository(private val db: ContactDao) {

    suspend fun insertContacts(vararg contact: ContactHasher) {
        withContext(Dispatchers.IO) {
            db.insertAllContact(*contact)
        }
    }

    fun getAllContacts(): LiveData<List<ContactHasher>> {
        return db.getAllContacts()
    }

}