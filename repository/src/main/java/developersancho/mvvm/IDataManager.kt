package developersancho.mvvm

import developersancho.mvvm.entity.FavEntity
import developersancho.mvvm.model.Repos
import developersancho.mvvm.network.ResponseWrapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

interface IDataManager {

    suspend fun getRepos(user: String): ResponseWrapper<ArrayList<Repos>>
    suspend fun insertFav(favEntity: FavEntity)
    suspend fun favList(): List<FavEntity>
    suspend fun fav(id: Int): FavEntity

    suspend fun getRepoList(user: String): Response<ArrayList<Repos>>

    fun getReposFromAPI(user: String): Flow<ResponseWrapper<ArrayList<Repos>>>

    fun getReposFromAPI2(user: String): Flow<ResponseWrapper<ArrayList<Repos>>>

    fun getReposFromAPI3(user: String): Flow<ResponseWrapper<ArrayList<Repos>>>

}