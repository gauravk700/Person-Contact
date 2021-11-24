package com.hashedIn.di

import android.app.Application
import androidx.room.Room
import com.hashedIn.adapter.ContactAdapter
import com.hashedIn.database.AppDatabase
import com.hashedIn.database.ContactDao
import com.hashedIn.http.APIService
import com.hashedIn.repository.ContactRepository
import com.hashedIn.viewModel.ContactDetailsViewModel
import com.hashedIn.viewModel.ContactListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.annotation.KoinReflectAPI
import org.koin.dsl.module
import org.koin.dsl.single
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


fun provideRoom(application: Application): AppDatabase =
    Room.databaseBuilder(application, AppDatabase::class.java, "ContactDataBase")
        .fallbackToDestructiveMigration()
        .build()

fun provideContactDao(db: AppDatabase): ContactDao = db.contactDao()

fun provideRetrofitInstance(): APIService = Retrofit.Builder().baseUrl(APIService.URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build().create(APIService::class.java)

val roomModule = module {
    single { provideRoom(get()) }
    single { provideContactDao(get()) }
}

val repositoryModule = module {
    single {
        ContactRepository(get())
    }
}

val retrofitModule = module {
    single { provideRetrofitInstance() }
}

val viewModelModule = module {
    viewModel {
        ContactListViewModel(get(), get())
    }

    viewModel {
        ContactDetailsViewModel()
    }
}

@KoinReflectAPI
val adapterModule = module {
    single<ContactAdapter>()

}