package com.hashedIn.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hashedIn.database.ContactHasher
import com.hashedIn.databinding.ContactViewHolderBinding
import com.hashedIn.di.Component

class ContactAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var list: List<ContactHasher> = emptyList()
    var clickListener: (ContactHasher) -> Unit = { }

    fun setList(contactHasher: List<ContactHasher>) {
        list = contactHasher
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ContactHolder(
            ContactViewHolderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ContactHolder)
            holder.bind(list[position], Component.contactAdapter)
    }

    override fun getItemCount(): Int = list.size

    fun onClickedView( contact: ContactHasher) = clickListener.invoke( contact)

    private class ContactHolder(val binding: ContactViewHolderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(contactHasher: ContactHasher, listener: ContactAdapter) {
            binding.apply {
                contact = contactHasher
                clickListener = listener
                executePendingBindings()
            }
        }
    }
}