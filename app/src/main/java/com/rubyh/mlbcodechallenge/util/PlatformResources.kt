package com.rubyh.mlbcodechallenge.util

import android.content.Context
import androidx.annotation.VisibleForTesting

@VisibleForTesting
class PlatformResources {
    fun loadResourceAsJson(resourceName: String, context: Context? = null): String? {
        return context?.let {
            (loadAsset(it, resourceName) ?: readSystemResource(resourceName))
        } ?: readResource(resourceName)
    }

    private fun getResourcesFromAssets(context: Context, resourcePath: String) : List<String>? {
        return try {
            context.assets.list(resourcePath)?.toList()
        } catch (e: Exception) {
            null
        }
    }

    private fun readResource(resourceName: String): String? {
        val classLoader = PlatformResources::class.java.getClassLoader()
        return classLoader?.getResourceAsStream(resourceName)
            ?.bufferedReader()?.readText()
    }


    private fun getSystemResources(resourcePath: String) : List<String>? {
        return ClassLoader.getSystemResources(resourcePath)?.toList()?.map {
            it.file
        }
    }

    private fun loadAsset(context: Context, fileName: String) : String? {
        return try {
            context.assets.open(fileName)
                .bufferedReader().use {
                    it.readText()
                }
        } catch (e: Exception) {
            null
        }
    }

    private fun readSystemResource(resourceName: String): String? {
        return ClassLoader.getSystemResourceAsStream(resourceName)
            ?.bufferedReader()?.readText()
    }
}