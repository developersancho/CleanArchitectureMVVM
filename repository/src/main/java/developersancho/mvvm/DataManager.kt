package developersancho.mvvm

import developersancho.mvvm.base.BaseDataManager
import developersancho.mvvm.model.Repos
import developersancho.mvvm.network.ResponseWrapper
import developersancho.mvvm.service.IRepoService

class DataManager(private val repoService: IRepoService) : BaseDataManager(), IDataManager {

    override suspend fun getRepos(user: String): ResponseWrapper<ArrayList<Repos>> =
        resultWrapperSuspend { repoService.repos(user = user) }


}