package developersancho.mvvm.network

sealed class ResponseWrapper<out T : Any> {
    class Success<out T : Any>(val data: T) : ResponseWrapper<T>()
    class Empty(val empty: String) : ResponseWrapper<Nothing>()
    class Error(val exception: RemoteDataException) : ResponseWrapper<Nothing>()
}