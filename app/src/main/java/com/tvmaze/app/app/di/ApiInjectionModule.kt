package com.tvmaze.app.app.di

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.joda.JodaModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.tvmaze.app.DebugDependencies
import okhttp3.OkHttpClient
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory

object ApiInjectionModule {

    private val BASE_URL = " http://api.tvmaze.com/"

    val module = Kodein.Module(ApiInjectionModule.javaClass.name) {

        bind<OkHttpClient>() with singleton {
            DebugDependencies
                .addOkHttpInterceptors(OkHttpClient.Builder())
                .build()
        }

        bind<ObjectMapper>() with singleton {
            jacksonObjectMapper().apply {
                registerModule(JodaModule())
                disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            }
        }

        bind<Retrofit>() with singleton {
            val okHttpClient = instance<OkHttpClient>()
            val jackson = instance<ObjectMapper>()

            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create(jackson))
                .build()
        }
    }
}
