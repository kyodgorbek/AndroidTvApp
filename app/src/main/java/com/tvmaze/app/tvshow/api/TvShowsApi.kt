package com.tvmaze.app.tvshow.api

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface TvShowsApi {

    @GET("search/shows")
    fun getTvShows(
        @Query("q") showName: String
    ): Single<List<SearchTvShowResponse>>
}