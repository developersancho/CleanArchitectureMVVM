package developersancho.mvvm

import developersancho.mvvm.model.Repos
import developersancho.mvvm.network.ResponseWrapper

interface IDataManager {

    suspend fun getRepos(user: String): ResponseWrapper<ArrayList<Repos>>

}