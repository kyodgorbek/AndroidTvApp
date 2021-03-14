package com.tvmaze.app.tvshow.domain

import com.tvmaze.app.tvshow.gateway.TvShowGateway
import io.reactivex.Single

class TvShowInteractor(
    private val tvShowGateway: TvShowGateway
) {

    fun searchForTvShows(showName: String): Single<List<TvShowItem>> =
        tvShowGateway.searchForTvShows(showName)
}