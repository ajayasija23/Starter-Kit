package com.app.starterkit.network

import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    private var retrofit: Retrofit? = null

    val logger=HttpLoggingInterceptor().apply {
        level=HttpLoggingInterceptor.Level.BODY
    }

    val httpClient=
        OkHttpClient.Builder()
            .readTimeout(1,TimeUnit.MINUTES)
            .writeTimeout(1,TimeUnit.MINUTES)
            .connectTimeout(1,TimeUnit.MINUTES)
            .addInterceptor(logger)
            .build()
    fun getRetrofitClient(baseUrl:String):Retrofit{
         if (retrofit==null){
             retrofit=Retrofit.Builder()
                 .baseUrl(baseUrl)
                 .addConverterFactory(MoshiConverterFactory.create().asLenient())
                 .client(httpClient)
                 .build()
         }
        return retrofit!!
    }

    val apiService:ApiInterface by lazy {
        getRetrofitClient("https://asija.000webhostapp.com/").create(ApiInterface::class.java)
    }
}