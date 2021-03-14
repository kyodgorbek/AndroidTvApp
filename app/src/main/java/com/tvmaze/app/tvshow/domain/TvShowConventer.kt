package com.tvmaze.app.tvshow.domain

import com.tvmaze.app.tvshow.api.SearchTvShowResponse

class TvShowConventer {

    fun fromRemote(showList: List<SearchTvShowResponse>): List<TvShowItem> {
        val result = mutableListOf<TvShowItem>()

        showList.forEach {
            it.show.name?.let { showName ->
                it.show.image?.original?.let { showImageUrl ->
                    it.show.network?.country?.name?.let { countryName ->
                        it.show.rating?.average?.let { showRating ->
                            TvShowItem(
                                showName = showName,
                                showImageUrl = showImageUrl,
                                showCountry = countryName,
                                averageRating = showRating,
                                genres = it.show.genres
                            )
                        }
                    }
                }
            }
                ?.let { tvShowItem -> result.add(tvShowItem) }
        }
        return result
    }
}