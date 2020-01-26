package com.developersancho.manager

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.developersancho.local.db.CoreDatabase
import com.developersancho.remote.service.IRepoService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

abstract class BaseManagerTest : KoinTest {
    protected lateinit var mockServer: MockWebServer
    protected lateinit var dataManager: DataManager

    private val db: CoreDatabase by inject()

    @Before
    open fun setUp() {
        this.configureMockServer()
        this.configureDi()
    }

    @After
    open fun tearDown() {
        this.stopMockServer()
        db.close()
        stopKoin()
    }

    private fun configureDi() {
        startKoin { modules(managerTestModule) }
    }

    private fun configureMockServer() {
        mockServer = MockWebServer()
        mockServer.start()
    }

    private fun stopMockServer() {
        mockServer.shutdown()
    }
}

val managerTestModule = module {

    // local
    val appContext = InstrumentationRegistry.getInstrumentation().targetContext

    single {
        Room.inMemoryDatabaseBuilder(appContext, CoreDatabase::class.java)
            .allowMainThreadQueries()
            .build()
    }

    factory { get<CoreDatabase>().favDao() }


    // remote
    single {
        OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    single {
        Retrofit.Builder()
            .client(get())
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    factory {
        get<Retrofit>().create(IRepoService::class.java)
    }
}


