package com.hashedIn.http

import retrofit2.http.GET

interface APIService {

    companion object{
        const val URL: String = ""
    }

    @GET("")
    suspend fun getContacts(){}
}