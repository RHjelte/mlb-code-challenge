package com.rubyh.mlbcodechallenge.model


import kotlinx.serialization.Serializable

@Serializable
data class MashTemp(
    val temp: Measurement?,
    val duration: Int?
) {
    override fun toString(): String {
        return "Mash Temp: $temp".plus(duration?.let {
            ", duration = $it"
        } ?: "")
    }
}