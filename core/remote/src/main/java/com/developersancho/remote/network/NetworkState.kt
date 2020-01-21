package com.developersancho.remote.network

sealed class NetworkState<out T : Any> {
    class Success<out T : Any>(val response: T) : NetworkState<T>()
    class Empty(val empty: String) : NetworkState<Nothing>()
    class Error(val exception: RemoteDataException) : NetworkState<Nothing>()
}