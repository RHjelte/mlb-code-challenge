package com.rubyh.mlbcodechallenge.repository

import com.rubyh.mlbcodechallenge.api.BeersApi
import com.rubyh.mlbcodechallenge.model.BeerDetails
import com.rubyh.mlbcodechallenge.util.ApiException
import com.rubyh.mlbcodechallenge.util.NetworkMonitor
import com.rubyh.mlbcodechallenge.util.NetworkNotAvailableException

class BeersRepository(
    private val beersApi: BeersApi,
    private val networkMonitor: NetworkMonitor
) {

    suspend fun fetchBeers(queryMap: Map<String, Any>) : Result<List<BeerDetails>> {
        if (!networkMonitor.isNetworkAvailable()) {
            return(Result.failure(NetworkNotAvailableException("Please enable network connectivity")))
        }

        return try {
            val result = beersApi.getBeers(queryMap)
            if (result.isSuccessful) {
                Result.success(result.body() as List<BeerDetails>)
            } else {
                Result.failure(ApiException("Punk v2 beers API failed", result.code()))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }

    }
}

