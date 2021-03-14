package com.tvmaze.app.search.ui

import androidx.lifecycle.ViewModel
import com.tvmaze.app.search.domain.SuggestionItem
import com.tvmaze.app.search.domain.SuggestionsInteractor
import com.tvmaze.app.tvshow.domain.TvShowInteractor
import com.tvmaze.app.tvshow.domain.TvShowItem
import io.reactivex.Flowable
import io.reactivex.Single

class SearchViewModel(
    private val tvShowInteractor: TvShowInteractor,
    private val suggestionsInteractor: SuggestionsInteractor
) : ViewModel() {

    fun searchTvShow(showName: String): Single<List<TvShowItem>> =
        tvShowInteractor.searchForTvShows(showName)

    fun listenToSuggestions(): Flowable<List<SuggestionItem>> {
        return suggestionsInteractor.listenToSuggestions()
    }

    fun saveSuggestion(suggestion:String) {
        suggestionsInteractor.insertSuggestionsItem(suggestion)
    }
}