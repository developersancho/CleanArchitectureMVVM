package com.developersancho.local.di

import androidx.room.Room
import com.developersancho.local.db.CoreDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

fun localModule(dbName: String) = module {

    single {
        Room.databaseBuilder(androidApplication(), CoreDatabase::class.java, dbName)
            .fallbackToDestructiveMigration()
            .build()
    }


    factory { get<CoreDatabase>().favDao() }
}