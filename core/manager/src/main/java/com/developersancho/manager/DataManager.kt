package com.developersancho.manager

import com.developersancho.local.dao.FavDao
import com.developersancho.manager.base.BaseDataManager
import com.developersancho.remote.model.RepoResponse
import com.developersancho.remote.network.NetworkState
import com.developersancho.remote.service.IRepoService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DataManager(
    private val repoService: IRepoService,
    private val favDao: FavDao
) : BaseDataManager(), IDataManager {

    override fun getRepos(userName: String): Flow<NetworkState<RepoResponse>> = flow {
        emit(apiCall { repoService.repos(userName) })
    }
}