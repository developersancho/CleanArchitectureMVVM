package developersancho.mvvm.di

import developersancho.mvvm.BuildConfig

val appModule = listOf(
    remoteModule(BuildConfig.BASE_URL),
    localModule(BuildConfig.DB_NAME),
    managerModule,
    viewModelModule
)