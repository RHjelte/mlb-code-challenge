package com.rubyh.mlbcodechallenge

import android.app.Application
import android.content.Context
import com.rubyh.mlbcodechallenge.di.appModule
import com.rubyh.mlbcodechallenge.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BeersApplication : Application() {

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@BeersApplication)
            modules(
                appModule,
                networkModule
            )
        }
    }

    companion object {
        private var instance : BeersApplication? = null

        fun requireContext() : Context {
            return instance!!.applicationContext
        }

    }
}