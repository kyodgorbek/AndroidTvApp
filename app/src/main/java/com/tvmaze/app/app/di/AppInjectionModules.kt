package com.tvmaze.app.app.di

import com.tvmaze.app.search.di.SearchInjectionModule
import com.tvmaze.app.tvshow.di.TvShowInjectionModule
import org.kodein.di.Kodein

object AppInjectionModules {

    val module = Kodein.Module(AppInjectionModules.javaClass.name) {
        import(DatabaseInjectionModule.module)
        import(ApiInjectionModule.module)
        import(TvShowInjectionModule.module)
        import(SearchInjectionModule.module)
    }
}