package com.rubyh.mlbcodechallenge.model


import kotlinx.serialization.Serializable

@Serializable
data class Measurement(
    val value: Double?,
    val unit: String?
) {
    override fun toString(): String {
        return value.toString().plus(" ").plus(unit)
    }
}