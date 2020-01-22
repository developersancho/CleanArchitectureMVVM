package developersancho.mvvm.di

import com.developersancho.local.di.localModule
import com.developersancho.manager.di.managerModule
import com.developersancho.remote.di.remoteModule
import developersancho.mvvm.BuildConfig

val appModule = listOf(
    remoteModule(BuildConfig.BASE_URL, isDebug = BuildConfig.DEBUG),
    localModule(BuildConfig.DB_NAME),
    managerModule,
    viewModelModule
)