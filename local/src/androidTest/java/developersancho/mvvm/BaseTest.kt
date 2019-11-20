package developersancho.mvvm

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import developersancho.mvvm.db.CoreDatabase
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject

@RunWith(AndroidJUnit4::class)
abstract class BaseTest : KoinTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()
    protected val database by inject<CoreDatabase>()

    @Before
    open fun setUp() {
        this.configureDi()
    }

    @After
    open fun tearDown() {
        stopKoin()
    }

    private fun configureDi() {
        startKoin { modules(localTestModule) }
    }
}

val localTestModule = module {
    single {
        Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext<Context>(),
            CoreDatabase::class.java
        ).allowMainThreadQueries().build()
    }
    single { get<CoreDatabase>().favDao() }
}