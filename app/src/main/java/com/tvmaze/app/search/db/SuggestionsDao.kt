package com.tvmaze.app.search.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RoomMasterTable.TABLE_NAME
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface SuggestionsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(suggestionItem: SuggestionEntity): Completable

    @Query("SELECT * FROM ${SuggestionEntity.TABLE_NAME} ORDER BY ${SuggestionEntity.COLUMN_TIMESTAMP} ")
    fun listenToSuggestions(): Flowable<List<SuggestionEntity>>
}