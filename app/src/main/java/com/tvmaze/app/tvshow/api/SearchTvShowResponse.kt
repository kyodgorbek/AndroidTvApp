package com.tvmaze.app.tvshow.api

data class SearchTvShowResponse(
    var score: Double,
    var show: Show
    )
data class Schedule (
    var time: String?,
    var days: List<String>?
    )

data class Rating (
    var average: Double = 0.0
    )

data class Country (
    var name: String?,
    var code: String?,
    var timezone: String?
    )

data class Network (
    var id: Int,
    var name: String?,
    var country: Country?
    )

data class WebChannel (
    var id: Int,
    var name: String?,
    var country: Any?
    )

data class Externals (
    var tvrage: Int,
    var thetvdb: Int,
    var imdb: String?
    )

data class Image (
    var medium: String?,
    var original: String?
    )

data class Self (
    var href: String?
    )

data class Previousepisode (
    var href: String?
    )

data class Nextepisode (
    var href: String?
    )

data class Links (
    var self: Self?,
    var previousepisode: Previousepisode?,
    var nextepisode: Nextepisode?
    )

data class Show (
    var id: Int,
    var url: String?,
    var name: String?,
    var type: String?,
    var language: String?,
    var genres: List<String>?,
    var status: String?,
    var runtime: Int,
    var premiered: String?,
    var officialSite: String?,
    var schedule: Schedule?,
    var rating: Rating?,
    var weight: Int,
    var network: Network?,
    var webChannel: WebChannel?,
    var externals: Externals?,
    var image: Image?,
    var summary: String?,
    var updated: Int,
    var _links: Links?
    )

