package com.developersancho.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.developersancho.local.entity.FavEntity

@Dao
interface FavDao : BaseDao<FavEntity> {

    @Query("DELETE FROM Fav_Table")
    suspend fun deleteAll()

    @Query("select * from Fav_Table")
    suspend fun favList(): List<FavEntity>

    @Query("select * from Fav_Table where favId= :id")
    suspend fun getFavById(id: Int): FavEntity

}