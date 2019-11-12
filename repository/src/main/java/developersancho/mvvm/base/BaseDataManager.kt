package developersancho.mvvm.base

import developersancho.mvvm.network.RemoteDataException
import developersancho.mvvm.network.ResponseWrapper
import kotlinx.coroutines.Deferred
import retrofit2.Response

abstract class BaseDataManager {

    protected suspend fun <T : Any> resultWrapperSuspend(call: suspend () -> Response<T>): ResponseWrapper<T> {
        return try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null)
                    ResponseWrapper.Success(body)
                else
                    ResponseWrapper.Empty("no json body")
            } else {
                ResponseWrapper.Error(RemoteDataException(response.errorBody()))
            }

        } catch (ex: Throwable) {
            ResponseWrapper.Error(RemoteDataException(ex))
        }
    }

    protected suspend inline fun <reified T : Any> resultWrapperDeferred(request: Deferred<Response<T>>): ResponseWrapper<T> {
        return try {
            val response = request.await()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null)
                    ResponseWrapper.Success(body)
                else
                    ResponseWrapper.Empty("no json body")
            } else {
                ResponseWrapper.Error(RemoteDataException(response.errorBody()))
            }
        } catch (ex: Throwable) {
            ResponseWrapper.Error(RemoteDataException(ex))
        }
    }
}