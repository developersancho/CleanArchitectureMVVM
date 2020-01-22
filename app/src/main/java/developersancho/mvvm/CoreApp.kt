package developersancho.mvvm

import android.content.Context
import androidx.multidex.MultiDexApplication
import developersancho.mvvm.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class CoreApp : MultiDexApplication() {

    companion object {
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        configureDi()
        configureTimber()
    }

    private fun configureTimber() {
        Timber.plant(Timber.DebugTree())
    }

    private fun configureDi() = startKoin {
        // use AndroidLogger as Koin Logger - default Level.INFO
        if (BuildConfig.DEBUG)
            androidLogger()
        // use the Android context given there
        androidContext(this@CoreApp)
        // load properties from assets/koin.properties file
        androidFileProperties()
        // module list
        modules(appModule)
    }
}