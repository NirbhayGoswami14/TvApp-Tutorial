package com.ncode.test2tvapp.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetroClient {

    private const val baseUrl="https://api.themoviedb.org/3/"
    const val BACKDROP_URL = "https://image.tmdb.org/t/p/w1280";
    const val POSTER_URL = "https://image.tmdb.org/t/p/w500/";
    const val ORIGINAL="https://image.tmdb.org/t/p/original/"
    fun getInstance():Services
    {
            var loggingInterceptor=HttpLoggingInterceptor()
        loggingInterceptor.level=HttpLoggingInterceptor.Level.BODY

        val okHttpClient=OkHttpClient.Builder()
            .connectTimeout(60,TimeUnit.MINUTES)
            .addInterceptor(loggingInterceptor)
            .readTimeout(60,TimeUnit.MINUTES)
            .build()
        val retrofit=Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        return retrofit.create(Services::class.java)
    }
}