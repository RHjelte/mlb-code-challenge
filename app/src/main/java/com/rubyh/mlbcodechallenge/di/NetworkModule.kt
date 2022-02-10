package com.rubyh.mlbcodechallenge.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.rubyh.mlbcodechallenge.api.BeersApi
import com.rubyh.mlbcodechallenge.util.NetworkMonitor
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit

@OptIn(ExperimentalSerializationApi::class)
val networkModule = module {

    // HttpLoggingInterceptor
    single {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    // OkHttpClient
    single {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }

    // Retrofit
    single<Retrofit> {
        Retrofit.Builder()
            .client(get<OkHttpClient>())
            .baseUrl("https://api.punkapi.com/v2/")
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    single<BeersApi> {
        BeersApi(get())
    }

    single { NetworkMonitor(get()) }
}
