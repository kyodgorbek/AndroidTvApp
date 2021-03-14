package com.tvmaze.app.tvshow.di

import com.tvmaze.app.tvshow.api.TvShowsApi
import com.tvmaze.app.tvshow.domain.TvShowInteractor
import com.tvmaze.app.tvshow.domain.TvShowConventer
import com.tvmaze.app.tvshow.gateway.TvShowGateway
import com.tvmaze.app.tvshow.ui.TvShowDetailsViewModel
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import retrofit2.Retrofit

object TvShowInjectionModule {

    val module = Kodein.Module(javaClass.name) {

        bind<TvShowDetailsViewModel>() with provider {
            TvShowDetailsViewModel()
        }

        bind<TvShowsApi>() with singleton {
            val retrofit = instance<Retrofit>()
            retrofit.create(TvShowsApi::class.java)
        }

        bind<TvShowGateway>() with singleton {
            TvShowGateway(instance(), instance())
        }

        bind<TvShowInteractor>() with singleton {
            TvShowInteractor(instance())
        }

        bind<TvShowConventer>() with singleton {
            TvShowConventer()
        }
    }
}