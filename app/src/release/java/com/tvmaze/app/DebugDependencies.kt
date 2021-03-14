package com.tvmaze.app

import android.app.Application
import android.os.StrictMode
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.myranks.app.BuildConfig
import com.myranks.app.R
import com.myranks.app.bluetooth.log.BluetoothLogModule
import com.facebook.stetho.Stetho
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.squareup.picasso.Picasso
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber

object DebugDependencies {

    fun addOkHttpInterceptors(builder: OkHttpClient.Builder): OkHttpClient.Builder {
        if (BuildConfig.DEBUG) {
            val httpLoggingInterceptor = HttpLoggingInterceptor()

            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            builder
                .addNetworkInterceptor(StethoInterceptor())
                .addNetworkInterceptor(httpLoggingInterceptor)
        }
        return builder
    }

    fun initOnAppCreateDependencies(app: Application) {
        // comment out incase need to test in release mode
//        setupTimber()

//        setupStrictMode()

//        setupStetho(app)
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
