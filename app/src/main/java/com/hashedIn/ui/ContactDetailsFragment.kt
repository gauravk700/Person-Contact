package com.hashedIn.ui

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.hashedIn.R
import com.hashedIn.databinding.FragmentContactDetailsBinding
import com.hashedIn.di.Component
import com.hashedIn.viewModel.SharedViewModel


class ContactDetailsFragment : Fragment() {

    private val model: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentContactDetailsBinding>(inflater, R.layout.fragment_contact_details, container, false)
        binding.apply {
            lifecycleOwner = this@ContactDetailsFragment.viewLifecycleOwner
            binding.detail = Component.contactDetailsViewModel
        }
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model.contactName.observe(this, {
            Component.contactDetailsViewModel.contactName.value = it
        })

        model.contactNumber.observe(this, {
            Component.contactDetailsViewModel.contactNumber.value = it
        })
    }

    private fun getShareIntent() : Intent {
//        val args = GameWonFragmentArgs.fromBundle(arguments)
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain")
//            .putExtra(Intent.EXTRA_TEXT, getString(R.string.share_success_text, args.numCorrect, args.numQuestions))
        return shareIntent
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_overflow, menu)
        if (null == getShareIntent().resolveActivity(activity!!.packageManager)) {
            menu.findItem(R.id.share)?.isVisible = false
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.share -> startActivity(getShareIntent())
        }
        return NavigationUI.onNavDestinationSelected(
            item, requireView().findNavController()
        ) || super.onOptionsItemSelected(item)
    }
}