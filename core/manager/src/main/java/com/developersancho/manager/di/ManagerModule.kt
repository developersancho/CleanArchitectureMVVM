package com.developersancho.manager.di

import com.developersancho.manager.DataManager
import com.developersancho.manager.IDataManager
import org.koin.dsl.module

val managerModule = module {
    factory {
        DataManager(get(), get()) as IDataManager
    }
}