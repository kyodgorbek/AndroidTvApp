package com.tvmaze.app.tvshow.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvShowItem(
    var showName: String,
    var showImageUrl: String,
    var showCountry: String,
    var averageRating: Double,
    var genres: List<String>?
) : Parcelable