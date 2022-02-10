package com.rubyh.mlbcodechallenge.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Method(
    @SerialName("mash_temp")
    val mashTemp: List<MashTemp>?,
    val fermentation: Fermentation?,
    val twist: String?
) {
    override fun toString(): String {
        return "Method:\n\t"
            .plus(mashTemp?.joinToString("\n\t") ?: "")
            .plus(fermentation?.let {
                "\n\t$it"
            } ?: "")
            .plus(twist?.let {
                "\n\tTwist: $it"
            } ?: "")
    }
}