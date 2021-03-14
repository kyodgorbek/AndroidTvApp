package com.tvmaze.app.search.domain

import com.tvmaze.app.search.repository.SuggestionsRepository
import io.reactivex.Flowable

class SuggestionsInteractor(
    private val suggestionsRepository: SuggestionsRepository
) {

    fun insertSuggestionsItem(suggestion: String) {
        suggestionsRepository.insertSuggestion(suggestion, System.currentTimeMillis())
    }

    fun listenToSuggestions(): Flowable<List<SuggestionItem>> {
        return suggestionsRepository.listenToSuggestions()
    }
}