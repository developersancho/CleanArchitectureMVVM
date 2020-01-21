package com.developersancho.remote.service

import com.developersancho.remote.model.RepoResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface IRepoService {

    @GET("users/{user}/repos")
    suspend fun repos(@Path("user") user: String): RepoResponse

}