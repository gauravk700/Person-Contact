package com.hashedIn.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ContactDetailsViewModel : ViewModel() {

    val contactName = MutableLiveData<String>()

    val contactNumber = MutableLiveData<String>()

}