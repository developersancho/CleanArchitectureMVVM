package developersancho.mvvm

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.okhttp.mockwebserver.MockResponse
import com.squareup.okhttp.mockwebserver.MockWebServer
import developersancho.mvvm.di.remoteModule
import developersancho.mvvm.service.IRepoService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.After
import org.junit.Before
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

abstract class BaseServiceTest : KoinTest {
    protected lateinit var mockServer: MockWebServer

    @Before
    open fun setUp() {
        this.configureMockServer()
        this.configureDi()
    }

    @After
    open fun tearDown() {
        this.stopMockServer()
        stopKoin()
    }

    private fun configureDi() {
        startKoin { modules(remoteTestModule) }
    }

    private fun configureMockServer() {
        mockServer = MockWebServer()
        mockServer.start()
    }

    private fun stopMockServer() {
        mockServer.shutdown()
    }

    fun mockHttpResponse(mockServer: MockWebServer, fileName: String, responseCode: Int) =
        mockServer.enqueue(
            MockResponse()
                .setResponseCode(responseCode)
                .setBody(getJson(fileName))
        )

    private fun getJson(path: String): String {
        val uri = this.javaClass.classLoader?.getResource(path)
        val file = File(uri?.path)
        return String(file.readBytes())
    }
}

val remoteTestModule = module {
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