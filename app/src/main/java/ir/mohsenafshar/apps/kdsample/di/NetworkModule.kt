package ir.mohsenafshar.apps.kdsample.di

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import ir.mohsenafshar.apps.kdsample.BuildConfig
import ir.mohsenafshar.apps.kdsample.data.remote.network.route.MovieApi
import ir.mohsenafshar.apps.kdsample.util.data.LiveDataCallAdapterFactory
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


const val API_KEY = "cdddafc1274c6dd00888eb661d7e9f91"
const val URL_DEV = "https://api.themoviedb.org/3/"
const val URL_PRODUCTION = URL_DEV

val BASE_URL = if (BuildConfig.BUILD_TYPE == "debug") URL_DEV else URL_PRODUCTION

val networkModule = module {

    val simpleOkHttp = "SIMPLE_OK_HTTP"
    val generalRetrofit = "GENERAL_RETROFIT"

    single { StethoInterceptor() }

    single<Gson> {
        GsonBuilder()
            .create()
    }

    single(named(generalRetrofit)) { getGeneralRetrofit(get(named(simpleOkHttp)), get()) }
    single(named(simpleOkHttp)) { provideSimpleOkHttp(get()) }

    single<MovieApi> { provideApi(get(named(generalRetrofit))) }
}

private fun provideSimpleOkHttp(
    stethoInterceptor: StethoInterceptor
): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(getInterceptor())
        .addInterceptor(provideLoggingInterceptor())
        .addNetworkInterceptor(stethoInterceptor)
        .callTimeout(15, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()
}

private fun getGeneralRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addCallAdapterFactory(LiveDataCallAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
}

private fun getInterceptor(): Interceptor {
    return Interceptor { chain ->
        val original: Request = chain.request()
        val originalHttpUrl: HttpUrl = original.url()
        val url = originalHttpUrl.newBuilder()
            .addQueryParameter("api_key", API_KEY)
            .addQueryParameter("language", "en-US")
            .build()

        // Request customization: add request headers
        val requestBuilder: Request.Builder = original.newBuilder()
            .url(url)
        val request: Request = requestBuilder.build()
        chain.proceed(request)
    }
}

private fun provideLoggingInterceptor(): HttpLoggingInterceptor {
    return HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
}

inline fun <reified T> provideApi(retrofit: Retrofit): T {
    return retrofit.create(T::class.java)
}