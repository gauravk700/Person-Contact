package com.hashedIn.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class ContactList(
    @SerializedName("contact_list")
    val list: List<ContactHasher>
)

@Entity(tableName ="hasherContact")
data class ContactHasher(
    @PrimaryKey(autoGenerate = false)
    @SerializedName("name")
    val name: String,
    @SerializedName("number")
    val number: String,
    @SerializedName("profile")
    val profile: String
)

