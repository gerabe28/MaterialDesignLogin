package com.example.materialdesign

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.acl.LastOwnerException
import java.util.logging.Level

/*
internal object MyApiAdapter {

    val client = OkHttpClient().newBuilder()
        .cache(cache)
        .addInterceptor(LastFmRequestInterceptor(apiKey,cacheDuration))

        .addInterceptor(HttpLoggingInterceptor().apply{
            level = if(BuildConfig.DEBUG) Level.BODY else
                Level.NONE
        })
        .build()


    val retrofit = Retrofit.Builder()
        .baseUrl("http://ws.audioscrobbler.com")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val lastFmService = retrofit.create(LastFmService::class.java)
}

//---------------****------------------//
*/
class LastFmRequestInterceptor(val apiKey: String, val cacheDuration: Int) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val url = request.url().newBuilder()
            .addQueryParameter("api_key", apiKey)
            .addQueryParameter("format", "json")
            .build()

        val newRequest = request.newBuilder()
            .url(url)
            .addHeader("Cache-Control", "public, max-age=$cacheDuration")
            .build()

        return chain.proceed(newRequest)
    }
}

// https://devexperto.com/retrofit-android-kotlin/