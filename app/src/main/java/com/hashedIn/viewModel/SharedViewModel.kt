package com.hashedIn.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel: ViewModel() {

    private val _contactName = MutableLiveData<String>()
    val contactName: LiveData<String>
        get() = _contactName

    private val _contactNumber = MutableLiveData<String>()
    val contactNumber: LiveData<String>
        get() = _contactNumber

    fun setName(name: String){
        _contactName.value = name
    }

    fun setNumber(name: String){
        _contactNumber.value = name
    }

}