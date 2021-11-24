package com.hashedIn.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.hashedIn.R
import com.hashedIn.database.ContactHasher
import com.hashedIn.database.ContactList
import com.hashedIn.repository.ContactRepository
import kotlinx.coroutines.*
import org.json.JSONException
import java.io.IOException

class ContactListViewModel(private val context: Context, private val repository: ContactRepository) : ViewModel() {

    private val job: CompletableJob = Job()

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    init {
        CoroutineScope(Dispatchers.Main + job).launch {
            parsing()
        }
    }

    private suspend fun parsing(){
        withContext(Dispatchers.IO){
            try {
                val jsonString = getJsonData()
                val model = Gson().fromJson(jsonString, ContactList::class.java)
                insertAllContacts(*model.list.toTypedArray())
            }catch (e: JSONException){
            }
        }
    }

    private suspend fun getJsonData(): String? {
        return withContext(Dispatchers.IO){
            val jsonString: String?
            try {
                jsonString = context.resources?.openRawResource(R.raw.contacts)?.bufferedReader().use { it?.readText() }
            } catch (ioException: IOException) {
                ioException.printStackTrace()
                return@withContext null
            }
            return@withContext jsonString
        }
    }

    private suspend fun insertAllContacts(vararg contact: ContactHasher ) =
        repository.insertContacts(*contact)

   fun getAllContacts(): LiveData<List<ContactHasher>> = repository.getAllContacts()

}