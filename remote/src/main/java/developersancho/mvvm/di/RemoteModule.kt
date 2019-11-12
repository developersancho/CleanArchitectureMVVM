package developersancho.mvvm.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import developersancho.mvvm.service.IRepoService
import developersancho.mvvm.util.NetworkUtils
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.*
import java.util.concurrent.TimeUnit

fun remoteModule(baseUrl: String) = module {

    single { File(androidContext().cacheDir, UUID.randomUUID().toString()) }

    single { Cache(get(), 5 * 1024 * 1024) }

    single {
        OkHttpClient.Builder()
            .cache(get())
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor { chain ->
                var request = chain.request().newBuilder()
                if (NetworkUtils.hasInternet(androidContext())) {
                    request.addHeader("Cache-Control", "public, max-age=" + 1)
                } else {
                    request.addHeader(
                        "Cache-Control",
                        "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7
                    )
                }

                chain.proceed(request.build())
            }
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    single {
        Retrofit.Builder()
            .client(get())
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    factory { get<Retrofit>().create(IRepoService::class.java) }
}