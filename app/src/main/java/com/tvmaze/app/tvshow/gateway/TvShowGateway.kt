package com.tvmaze.app.tvshow.gateway

import com.tvmaze.app.tvshow.api.TvShowsApi
import com.tvmaze.app.tvshow.domain.TvShowConventer
import com.tvmaze.app.tvshow.domain.TvShowItem
import io.reactivex.Single

class TvShowGateway(
    private val tvShowsApi: TvShowsApi,
    private val convener: TvShowConventer
) {

    fun searchForTvShows(showName: String): Single<List<TvShowItem>> =
        tvShowsApi
            .getTvShows(showName = showName)
            .map { convener.fromRemote(it) }

}