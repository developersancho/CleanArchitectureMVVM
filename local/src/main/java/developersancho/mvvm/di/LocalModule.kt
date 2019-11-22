package developersancho.mvvm.di

import androidx.room.Room
import developersancho.mvvm.db.CoreDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

fun localModule(dbName: String) = module {

    single {
        Room.databaseBuilder(androidApplication(), CoreDatabase::class.java, dbName)
            .fallbackToDestructiveMigration()
            .build()
    }

    single { get<CoreDatabase>().favDao() }

}