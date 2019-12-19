package developersancho.mvvm

import developersancho.mvvm.dao.FavDao
import developersancho.mvvm.network.ResponseWrapper
import developersancho.mvvm.service.IRepoService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.koin.test.inject
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import timber.log.Timber

@ExperimentalCoroutinesApi
class DataManagerTest : BaseDataManagerTest() {

    private val repoService by inject<IRepoService>()

    @Mock
    lateinit var favDao: FavDao

    private var user = "developersancho"

    @Before
    fun setUpData() {
        MockitoAnnotations.initMocks(this)
        dataManager = DataManager(repoService, favDao)
    }

    @Test
    fun `Get Repo`() = runBlocking {
        dataManager.getReposFromAPI(user).collect {
            when (it) {
                is ResponseWrapper.Success -> {
                    Assert.assertEquals(30, it.data.size)
                }

                is ResponseWrapper.Error -> {
                    Timber.d(it.exception.message)
                }
            }
        }
    }


    @Test
    fun `Get Repo2`() = runBlocking {
        dataManager.getReposFromAPI2(user).collect {
            when (it) {
                is ResponseWrapper.Success -> {
                    Assert.assertEquals(30, it.data.size)
                }

                is ResponseWrapper.Error -> {
                    Timber.d(it.exception.message)
                }
            }
        }
    }

    @Test
    fun `Get Repo3`() = runBlocking {
        dataManager.getReposFromAPI3(user).collect {
            when (it) {
                is ResponseWrapper.Success -> {
                    Assert.assertEquals(30, it.data.size)
                }

                is ResponseWrapper.Error -> {
                    Timber.d(it.exception.message)
                }
            }
        }
    }

}