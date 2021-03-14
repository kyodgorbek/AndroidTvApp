package com.tvmaze.app.search.repository

import android.annotation.SuppressLint
import com.tvmaze.app.search.db.SuggestionEntity
import com.tvmaze.app.search.db.SuggestionsDao
import com.tvmaze.app.search.domain.SuggestionItem
import com.tvmaze.app.search.domain.SuggestionsConverter
import io.reactivex.Flowable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class SuggestionsRepository(
    private val suggestionsDao: SuggestionsDao,
    private val suggestionsConverter: SuggestionsConverter
) {

    @SuppressLint("CheckResult")
    fun insertSuggestion(suggestion: String, timestamp: Long) {
        suggestionsDao.insert(
            SuggestionEntity(
                suggestion = suggestion,
                timestamp = timestamp
            )
        )
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onError = Timber::e
            )
    }

    fun listenToSuggestions(): Flowable<List<SuggestionItem>> {
        return suggestionsDao.listenToSuggestions()
            .map { suggestionsConverter.fromDatabase(it) }
    }
}