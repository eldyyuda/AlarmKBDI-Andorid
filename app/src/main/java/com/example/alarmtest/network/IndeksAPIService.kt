package com.example.alarmtest.network

import com.example.alarmtest.models.Item
import com.example.alarmtest.models.Kbdi
import com.example.alarmtest.models.ListRequest
import com.example.alarmtest.models.ListResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor


private const val BASE_URL = "http://192.168.43.150:8000/"

private val headerInterceptor = object : Interceptor {
    // Interceptor for adding Header
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder()
            .addHeader("Accept", "application/json")
            .addHeader("Content-Type", " application/json")
            .build()
        return chain.proceed(request)
    }
}

private fun logging(): HttpLoggingInterceptor {
    // Interceptor for adding Logging (DEBUGGER)
    // Disabled when production
    val logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.NONE
    return logging
}

private fun getInterceptor(): OkHttpClient =
    OkHttpClient.Builder().addInterceptor(logging()).addInterceptor(headerInterceptor).build()

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .client(getInterceptor())
    .baseUrl(BASE_URL)
    .build()


interface IndeksAPIService {
    @GET("/kbdis/api")
    fun getDataAsync(): Deferred<ListResponse>

//    @POST("/index/post")
//    suspend fun postDataAsync(@Body kbdi: Kbdi): Deferred<ListResponse>
//    @POST("/itemscreate/")
//    suspend fun postDataAsync(@Body item: Item): Item
}


object IndexApi {
    val retrofitService: IndeksAPIService by lazy {
        retrofit.create(IndeksAPIService::class.java)
    }

}