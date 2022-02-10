package com.rubyh.mlbcodechallenge.model


import kotlinx.serialization.Serializable

@Serializable
data class Fermentation(
    val temp: Measurement?
) {
    override fun toString(): String {
        return "Fermentation: $temp"
    }
}