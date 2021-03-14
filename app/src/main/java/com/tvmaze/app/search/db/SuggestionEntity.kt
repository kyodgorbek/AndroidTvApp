package com.tvmaze.app.search.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = SuggestionEntity.TABLE_NAME,
    indices = [Index(value = [SuggestionEntity.COLUMN_SUGGESTION], unique = true)]
)
data class SuggestionEntity(

    @PrimaryKey
    @ColumnInfo(name = COLUMN_SUGGESTION)
    val suggestion: String,

    @ColumnInfo(name = COLUMN_TIMESTAMP)
    val timestamp: Long
) {
    companion object {
        const val TABLE_NAME = "suggestions"
        const val COLUMN_SUGGESTION = "suggestion"
        const val COLUMN_TIMESTAMP = "timestamp"
    }
}