package com.developersancho.manager

import android.os.Build
import com.developersancho.local.dao.FavDao
import com.developersancho.remote.network.NetworkState
import com.developersancho.remote.service.IRepoService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.inject
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import timber.log.Timber


@ExperimentalCoroutinesApi
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class ManagerTest : BaseManagerTest() {

    private val repoService by inject<IRepoService>()
    private val favDao by inject<FavDao>()


    @Before
    fun setUpData() {
        MockitoAnnotations.initMocks(this)
        dataManager = DataManager(repoService, favDao)
    }

    @Test
    fun `Get Repos By UserName`() = runBlocking {
        dataManager.getRepos("developersancho").collect {
            when (it) {
                is NetworkState.Success -> {
                    Assert.assertEquals(30, it.response?.size)
                    Assert.assertEquals(117545989, it.response?.first()?.id)
                    Assert.assertEquals("a4app", it.response?.first()?.name)
                }

                is NetworkState.Error -> {
                    Timber.d("NetworkState.Error : ${it.exception.message}")
                }
            }
        }
    }

}