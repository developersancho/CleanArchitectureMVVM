package developersancho.mvvm

import developersancho.mvvm.service.IRepoService
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.test.inject
import java.net.HttpURLConnection

@RunWith(JUnit4::class)
class RepoServiceTest : BaseServiceTest() {

    private val repoService by inject<IRepoService>()

    @Test
    fun `search repo`() {
        mockHttpResponse(mockServer, "user_repos.json", HttpURLConnection.HTTP_OK)
        runBlocking {
            val response = repoService.repos("developersancho").body()

            Assert.assertEquals(30, response?.size)
            Assert.assertEquals(117545989, response?.first()?.id)
            Assert.assertEquals("a4app", response?.first()?.name)
        }
    }

    @Test
    fun `search repo and not found`() {
        mockHttpResponse(mockServer, "user_repos.json", HttpURLConnection.HTTP_NOT_FOUND)
        runBlocking {
            repoService.repos("developersanchos")
        }
    }
}