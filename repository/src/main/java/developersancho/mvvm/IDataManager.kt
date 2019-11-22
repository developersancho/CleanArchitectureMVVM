package developersancho.mvvm

import developersancho.mvvm.entity.FavEntity
import developersancho.mvvm.model.Repos
import developersancho.mvvm.network.ResponseWrapper

interface IDataManager {

    suspend fun getRepos(user: String): ResponseWrapper<ArrayList<Repos>>
    suspend fun insertFav(favEntity: FavEntity)
    suspend fun favList(): List<FavEntity>
    suspend fun fav(id: Int): FavEntity

}