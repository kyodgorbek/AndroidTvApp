package com.tvmaze.app

import android.app.Application
import android.os.StrictMode
import com.facebook.stetho.Stetho
import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber

object DebugDependencies {

    fun addOkHttpInterceptors(builder: OkHttpClient.Builder): OkHttpClient.Builder {
        val httpLoggingInterceptor = HttpLoggingInterceptor()

        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        builder
            .addNetworkInterceptor(StethoInterceptor())
            .addNetworkInterceptor(httpLoggingInterceptor)
        return builder
    }

    fun initOnAppCreateDependencies(app: Application) {
        setupTimber()

        setupStrictMode()

        setupStetho(app)
    }

    private fun setupStrictMode() {
        StrictMode.setThreadPolicy(
            StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build()
        )

        StrictMode.setVmPolicy(
            StrictMode.VmPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build()
        )
    }

    private fun setupTimber() {
        Timber.plant(Timber.DebugTree())
    }

    private fun setupStetho(app: Application) {
        Stetho.initializeWithDefaults(app)
    }
}