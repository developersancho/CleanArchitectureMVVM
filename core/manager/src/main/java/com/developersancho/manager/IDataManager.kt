package com.developersancho.manager

import com.developersancho.local.entity.FavEntity
import com.developersancho.remote.model.RepoResponse
import com.developersancho.remote.network.NetworkState
import kotlinx.coroutines.flow.Flow

interface IDataManager {

    fun getRepos(userName: String): Flow<NetworkState<RepoResponse>>

    suspend fun insertFav(favEntity: FavEntity)

    suspend fun favList(): List<FavEntity>

}