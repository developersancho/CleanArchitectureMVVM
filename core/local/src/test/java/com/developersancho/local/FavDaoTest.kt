package com.developersancho.local

import android.os.Build
import com.developersancho.local.dao.FavDao
import com.developersancho.local.entity.FavEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.inject
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import timber.log.Timber


@ExperimentalCoroutinesApi
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class FavDaoTest : BaseLocalTest() {

    private val favDao by inject<FavDao>()

    @Test
    fun insertFav() = runBlocking {

        favDao.insert(FavEntity(favId = 1, isFav = true))

        val favList = favDao.favList()

        Timber.d("size ------> ${favList.size}")

        Assert.assertEquals(1, favList.size)

    }

}