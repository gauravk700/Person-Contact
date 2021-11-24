package com.hashedIn.di

import com.hashedIn.adapter.ContactAdapter
import com.hashedIn.viewModel.ContactDetailsViewModel
import com.hashedIn.viewModel.ContactListViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


object Component: KoinComponent {

    val contactAdapter: ContactAdapter by inject()
    val contactListViewModel: ContactListViewModel by inject()
    val contactDetailsViewModel: ContactDetailsViewModel by inject()

}