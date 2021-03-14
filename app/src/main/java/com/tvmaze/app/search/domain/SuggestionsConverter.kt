package com.tvmaze.app.search.domain

import com.tvmaze.app.search.db.SuggestionEntity

class SuggestionsConverter {

    fun fromDatabase(suggestions: List<SuggestionEntity>) =
        suggestions.map { fromDatabase(it) }

    fun fromDatabase(suggestionEntity: SuggestionEntity) =
        SuggestionItem(text = suggestionEntity.suggestion)

}
