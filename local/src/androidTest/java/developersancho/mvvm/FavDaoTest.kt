package developersancho.mvvm

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import developersancho.mvvm.entity.FavEntity
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FavDaoTest : BaseTest() {

    @Test
    fun insertFavRepo() {
        runBlocking {
            database.favDao().insert(
                FavEntity(
                    favId = 117545989,
                    isFav = true
                )
            )

            val fav = database.favDao().fav(117545989)
            Assert.assertEquals(117545989, fav.favId)
            Assert.assertEquals(true, fav.isFav)
        }
    }


    @Test
    fun getFavRepo() {
        runBlocking {
            val fav = database.favDao().fav(117545989)
            Assert.assertEquals(117545989, fav.favId)
            Assert.assertEquals(true, fav.isFav)
        }
    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        Assert.assertEquals("developersancho.mvvm.test", appContext.packageName)
    }

}