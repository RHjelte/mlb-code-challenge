package com.rubyh.mlbcodechallenge.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/*
 * BeerDetails class was generated by JSON to Kotlin Class plugin. Most fields
 * are defined as nullable to prevent exceptions on null values when decoding
 * from JSON.
 */
@Serializable
data class BeerDetails(
    val id: Int,
    val name: String,
    val tagline: String?,
    @SerialName("first_brewed")
    val firstBrewed: String?,
    val description: String?,
    @SerialName("image_url")
    val imageUrl: String?,
    val abv: Double?,
    val ibu: Double?,
    @SerialName("target_fg")
    val targetFg: Double?,
    @SerialName("target_og")
    val targetOg: Double?,
    val ebc: Double?,
    val srm: Double?,
    val ph: Double?,
    @SerialName("attenuation_level")
    val attenuationLevel: Double?,
    val volume: Measurement?,
    @SerialName("boil_volume")
    val boilVolume: Measurement?,
    val method: Method?,
    val ingredients: Ingredients?,
    @SerialName("food_pairing")
    val foodPairing: List<String>?,
    @SerialName("brewers_tips")
    val brewersTips: String?,
    @SerialName("contributed_by")
    val contributedBy: String?
)