package com.rubyh.mlbcodechallenge.api

import com.rubyh.mlbcodechallenge.model.BeerDetails
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.QueryMap

class BeersApi(val retrofit: Retrofit) {
    val apiClient by lazy {
        retrofit.create(BeersApiService::class.java)
    }

    suspend fun getBeers(queryParams: Map<String, Any>): Response<List<BeerDetails>> {
        return apiClient.getBeers(queryParams)
    }
}

interface BeersApiService {
    @JvmSuppressWildcards     // https://github.com/square/retrofit/issues/3275
    @GET("beers")
    suspend fun getBeers(@QueryMap options: Map<String, Any>): Response<List<BeerDetails>>
}