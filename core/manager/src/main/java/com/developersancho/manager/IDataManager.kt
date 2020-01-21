package com.developersancho.manager

import com.developersancho.remote.model.RepoResponse
import com.developersancho.remote.network.NetworkState
import kotlinx.coroutines.flow.Flow

interface IDataManager {

    fun getRepos(userName: String): Flow<NetworkState<RepoResponse>>

}