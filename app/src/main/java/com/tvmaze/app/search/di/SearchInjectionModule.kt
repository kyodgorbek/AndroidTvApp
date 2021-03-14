package com.tvmaze.app.search.di

import com.tvmaze.app.search.db.SuggestionsDao
import com.tvmaze.app.search.db.TvMazeDatabase
import com.tvmaze.app.search.domain.SuggestionsConverter
import com.tvmaze.app.search.domain.SuggestionsInteractor
import com.tvmaze.app.search.repository.SuggestionsRepository
import com.tvmaze.app.search.ui.SearchViewModel
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

object SearchInjectionModule {

    val module = Kodein.Module(javaClass.name) {

        bind<SuggestionsInteractor>() with provider {
            SuggestionsInteractor(instance())
        }

        bind<SuggestionsConverter>() with provider {
            SuggestionsConverter()
        }

        bind<SearchViewModel>() with provider {
            SearchViewModel(instance(), instance())
        }

        bind<SuggestionsDao>() with singleton {
            instance<TvMazeDatabase>().suggestionsDao
        }

        bind<SuggestionsRepository>() with singleton {
            SuggestionsRepository(instance(), instance())
        }
    }
}