package com.hashedIn.utils


import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.hashedIn.R
import com.hashedIn.database.ContactHasher


@BindingAdapter("contactName")
fun TextView.setContactName(contactHasher: ContactHasher?) {
    contactHasher?.let {
        text = contactHasher.name
    }
}

@BindingAdapter("contactNumber")
fun setContactNumber(textView: TextView, contactHasher: ContactHasher?) {
    contactHasher?.let {
        textView.text = contactHasher.number
    }
}


@BindingAdapter("contactImage")
fun ImageView.setContactImage(url: String) {
    Log.i("glide", "calling")
    val options: RequestOptions = RequestOptions()
        .centerCrop()
        .placeholder(R.mipmap.ic_launcher_round)
        .error(R.mipmap.ic_launcher_round)
    Glide.with(this)
        .load(url)
        .apply(options).into(this)
}