package com.hashedIn.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.hashedIn.R
import com.hashedIn.database.ContactHasher
import com.hashedIn.databinding.FragmentContactsListBinding
import com.hashedIn.di.Component
import com.hashedIn.viewModel.SharedViewModel

class ContactsListFragment : Fragment() {

    private val model: SharedViewModel by activityViewModels()
    private lateinit var binding: FragmentContactsListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentContactsListBinding.inflate(inflater, container, false)
        return binding.also {
            it.lifecycleOwner = this@ContactsListFragment.viewLifecycleOwner
            it.contactListBinding = Component.contactListViewModel
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Component.apply {
            contactListViewModel.apply {
                getAllContacts().observe(viewLifecycleOwner, Observer { list ->
                    contactAdapter.also {
                        binding.rvContactList.adapter = it
                        if (!list.isNullOrEmpty()) {
                            it.setList(list)
                            it.clickListener = { contactHasher: ContactHasher ->
                                model.apply {
                                    setName(contactHasher.name)
                                    setNumber(contactHasher.number)
                                }
                                view.findNavController()
                                    .navigate(R.id.action_contactsListFragment_to_contactDetailsFragment)
                            }
                        }
                    }
                })
            }
        }
    }


}