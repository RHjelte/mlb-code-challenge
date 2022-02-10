package com.rubyh.mlbcodechallenge.model


import kotlinx.serialization.Serializable

@Serializable
data class Hop(
    val name: String?,
    val amount: Measurement?,
    val add: String?,
    val attribute: String?
) {
    override fun toString(): String {
        return "$amount $name, add: $add, attribute: $attribute"
    }
}