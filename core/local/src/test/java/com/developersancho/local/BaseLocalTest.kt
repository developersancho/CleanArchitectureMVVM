package com.developersancho.local

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.developersancho.local.db.CoreDatabase
import org.junit.After
import org.junit.Before
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject

abstract class BaseLocalTest : KoinTest {

    private val coreDB: CoreDatabase by inject()

    @Before
    open fun setUp() {
        this.configureDi()
    }

    @After
    open fun tearDown() {
        coreDB.close()
        stopKoin()
    }

    private fun configureDi() {
        startKoin { modules(localTestModule) }
    }

}

val localTestModule = module {
    /* val context = ApplicationProvider.getApplicationContext<Context>() */
    val appContext = InstrumentationRegistry.getInstrumentation().targetContext

    single {
        Room.inMemoryDatabaseBuilder(appContext, CoreDatabase::class.java)
            .allowMainThreadQueries()
            .build()
    }

    factory { get<CoreDatabase>().favDao() }

}
