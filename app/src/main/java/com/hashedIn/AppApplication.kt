package com.hashedIn

import android.app.Application
import com.hashedIn.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class AppApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AppApplication)
            val modulesArray = arrayOf(roomModule, repositoryModule, viewModelModule, adapterModule, retrofitModule)
            modules(*modulesArray)
        }
    }
}