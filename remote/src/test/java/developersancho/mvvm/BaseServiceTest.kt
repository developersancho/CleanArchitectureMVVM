package developersancho.mvvm

import com.squareup.okhttp.mockwebserver.MockResponse
import com.squareup.okhttp.mockwebserver.MockWebServer
import developersancho.mvvm.di.remoteModule
import org.junit.After
import org.junit.Before
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import java.io.File

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
        startKoin { modules(remoteModule("https://api.github.com/")) }
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