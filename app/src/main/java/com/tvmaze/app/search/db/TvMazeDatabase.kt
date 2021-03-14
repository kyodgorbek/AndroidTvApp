package com.tvmaze.app.search.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [SuggestionEntity::class], version = 6, exportSchema = false)
abstract class TvMazeDatabase : RoomDatabase() {

    abstract val suggestionsDao: SuggestionsDao

}