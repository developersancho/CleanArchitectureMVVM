package developersancho.mvvm.di

import developersancho.mvvm.DataManager
import developersancho.mvvm.IDataManager
import org.koin.dsl.module

val managerModule = module {
    factory { DataManager(get(), get()) as IDataManager }
}