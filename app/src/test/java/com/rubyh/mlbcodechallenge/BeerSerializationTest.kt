package com.rubyh.mlbcodechallenge

import com.rubyh.mlbcodechallenge.model.BeerDetails
import com.rubyh.mlbcodechallenge.util.PlatformResources
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.junit.Test

class BeerSerializationTest {

    @Test
    fun `decode beer details`() {
        val list = PlatformResources()
            .loadResourceAsJson("beers.json")
            ?.let {
                Json.decodeFromString<List<BeerDetails>>(it)
            }.orEmpty()
        assert(list.isNotEmpty())
    }
}