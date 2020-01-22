package com.developersancho.manager.base

import com.developersancho.remote.network.NetworkState
import com.developersancho.remote.network.RemoteDataException
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.delay
import retrofit2.Response
import java.io.IOException

abstract class BaseDataManager {

    // TODO("add retry policy and local(db) cache via remote service method")

    protected suspend fun <T : Any> apiCallResponse(call: suspend () -> Response<T>): NetworkState<T> {
        return try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null)
                    NetworkState.Success(body)
                else
                    NetworkState.Empty("no json body")
            } else {
                NetworkState.Error(RemoteDataException(response.errorBody()))
            }

        } catch (ex: Throwable) {
            NetworkState.Error(RemoteDataException(ex))
        }
    }

    protected suspend fun <T : Any> apiCall(call: suspend () -> T): NetworkState<T> {
        return try {
            val response = call()
            NetworkState.Success(response)
        } catch (ex: Throwable) {
            NetworkState.Error(RemoteDataException(ex))
        }
    }

    protected suspend fun <T : Any> apiCallRetry(
        times: Int = 3,
        call: suspend () -> T
    ): NetworkState<T> {
        return try {
            val response = apiCallIO(timesValue = times, block = call)
            NetworkState.Success(response)
        } catch (ex: Throwable) {
            NetworkState.Error(RemoteDataException(ex))
        }
    }

    private suspend fun <T> apiCallIO(
        timesValue: Int = 4,
        initialDelay: Long = 100, // 0.1 second
        maxDelay: Long = 1000,    // 1 second
        factor: Double = 2.0,
        block: suspend () -> T
    ): T {
        var currentDelay = initialDelay
        repeat(timesValue - 1) {
            try {
                return block()
            } catch (e: IOException) {
                // you can log an error here and/or make a more finer-grained
                // analysis of the cause to see if retry is needed
            }
            delay(currentDelay)
            currentDelay = (currentDelay * factor).toLong().coerceAtMost(maxDelay)
        }
        return block() // last attempt
    }

    protected suspend inline fun <reified T : Any> apiCallDeferred(request: Deferred<Response<T>>): NetworkState<T> {
        return try {
            val response = request.await()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null)
                    NetworkState.Success(body)
                else
                    NetworkState.Empty("no json body")
            } else {
                NetworkState.Error(RemoteDataException(response.errorBody()))
            }
        } catch (ex: Throwable) {
            NetworkState.Error(RemoteDataException(ex))
        }
    }
}