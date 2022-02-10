package com.rubyh.mlbcodechallenge.model


import kotlinx.serialization.Serializable

@Serializable
data class Ingredients(
    val malt: List<Malt>?,
    val hops: List<Hop>?,
    val yeast: String?
) {
    override fun toString(): String {
        val malt = malt?.let {
            "\n\tMalt:\n\t\t".plus(it.joinToString("\n\t\t"))
        } ?: ""
        val hops = hops?.let {
            "\n\tHops:\n\t\t".plus(it.joinToString("\n\t\t"))
        } ?: ""
        val yeast = yeast?.let {
            "\n\tYeast: $it"
        } ?: ""

        return "Ingredients:".plus(malt).plus(hops).plus(yeast)
    }
}