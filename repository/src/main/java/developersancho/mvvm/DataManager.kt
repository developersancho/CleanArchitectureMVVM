package developersancho.mvvm

import developersancho.mvvm.base.BaseDataManager
import developersancho.mvvm.dao.FavDao
import developersancho.mvvm.entity.FavEntity
import developersancho.mvvm.model.Repos
import developersancho.mvvm.network.RemoteDataException
import developersancho.mvvm.network.ResponseWrapper
import developersancho.mvvm.service.IRepoService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

class DataManager(
    private val repoService: IRepoService,
    private val favDao: FavDao
) : BaseDataManager(), IDataManager {

    override suspend fun getRepos(user: String): ResponseWrapper<ArrayList<Repos>> =
        resultWrapperSuspend { repoService.repos(user = user) }

    override suspend fun insertFav(favEntity: FavEntity) = favDao.insert(favEntity)

    override suspend fun favList(): List<FavEntity> = favDao.favList()

    override suspend fun fav(id: Int): FavEntity = favDao.fav(id)

    override suspend fun getRepoList(user: String): Response<ArrayList<Repos>> =
        repoService.repos(user = user)

    override fun getReposFromAPI(user: String) = flow {
        emit(resultWrapperSuspend { repoService.repos(user = user) })
    }

    override fun getReposFromAPI2(user: String): Flow<ResponseWrapper<ArrayList<Repos>>> {
        return flow {
            repoService.repos2(user = user).run {
                try {
                    emit(ResponseWrapper.Success(this))
                } catch (exception: Exception) {
                    emit(ResponseWrapper.Error(RemoteDataException(exception)))
                }
            }
        }
    }

    override fun getReposFromAPI3(user: String) = flow {
        emit(resultWrapperSuspend2 { repoService.repos2(user = user) })
    }
}
