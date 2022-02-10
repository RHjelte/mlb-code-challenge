package com.rubyh.mlbcodechallenge.model


import kotlinx.serialization.Serializable

@Serializable
data class Malt(
    val name: String?,
    val amount: Measurement?
) {
    override fun toString(): String {
        return "$amount $name"
    }
}