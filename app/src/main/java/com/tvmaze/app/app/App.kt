package com.tvmaze.app.app

import android.app.Application
import com.tvmaze.app.BuildConfig
import com.tvmaze.app.DebugDependencies
import com.tvmaze.app.app.di.AppInjectionModules
import io.reactivex.exceptions.UndeliverableException
import io.reactivex.plugins.RxJavaPlugins
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.conf.ConfigurableKodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton
import timber.log.Timber
import java.io.InterruptedIOException

class App : Application(), KodeinAware {

    override val kodein = ConfigurableKodein()

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            DebugDependencies.initOnAppCreateDependencies(this)
        }

        setupDependencyInjection()

        setupRx()
    }

    private fun setupDependencyInjection() {
        kodein.apply {

            mutable = true

            clear()

            addImport(AppInjectionModules.module)
            addImport(Kodein.Module(javaClass.name) {
                bind<Application>() with singleton { this@App }
            })
        }
    }

    private fun setupRx() {
        RxJavaPlugins.setErrorHandler {
            when {
                it is UndeliverableException -> Timber.d(it)
                it is InterruptedIOException -> Timber.d(it)
                it.cause?.cause is InterruptedIOException -> Timber.d(it)
                else -> Timber.e(it, "Unhandled RxJava exception")
            }
        }
    }
}
