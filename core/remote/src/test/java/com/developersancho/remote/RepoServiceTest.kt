package com.developersancho.remote

import com.developersancho.remote.service.IRepoService
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.test.inject


@RunWith(JUnit4::class)
class RepoServiceTest : BaseRemoteTest() {

    private val service by inject<IRepoService>()

    @Test
    fun search_repo() = runBlocking {
        val response = service.repos(user = "developersancho")

        Assert.assertEquals(30, response?.size)
        Assert.assertEquals(117545989, response?.first()?.id)
        Assert.assertEquals("a4app", response?.first()?.name)
    }

}