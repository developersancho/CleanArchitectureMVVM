package developersancho.cleanarchitecturemvvm

import android.app.Application
import android.content.Context

class CoreApp : Application() {

    companion object {
        lateinit var context: Context
    }


    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}