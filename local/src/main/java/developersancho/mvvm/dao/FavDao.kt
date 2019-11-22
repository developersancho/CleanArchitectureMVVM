package developersancho.mvvm.dao

import androidx.room.Dao
import androidx.room.Query
import developersancho.mvvm.dao.BaseDao
import developersancho.mvvm.entity.FavEntity

@Dao
interface FavDao : BaseDao<FavEntity> {
    @Query("DELETE FROM Fav_Table")
    suspend fun deleteAll()

    @Query("select * from Fav_Table")
    suspend fun favList(): List<FavEntity>

    @Query("select * from Fav_Table where favId= :id")
    suspend fun fav(id: Int): FavEntity
}