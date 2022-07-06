package com.my.movies.data.dto

import com.google.gson.annotations.SerializedName
import com.my.movies.data.storage.dto.NowPlayingEntity
import com.my.movies.domain.Movie

internal data class MovieDto(
    @SerializedName(value = "poster_path")
    private val posterPath: String?,
    private val adult: Boolean?,
    private val overview: String?,
    @SerializedName(value = "release_date")
    private val releaseDate: String?,
    @SerializedName(value = " genre_ids")
    private val genreIds: List<Int>?,
    private val id: Int?,
    @SerializedName(value = "original_title")
    private val originalTitle: String?,
    @SerializedName(value = "original_language")
    private val originalLanguage: String?,
    private val title: String?,
    @SerializedName(value = "backdrop_path")
    private val backdropPath: String?,
    private val popularity: Float?,
    @SerializedName(value = "vote_count")
    private val voteCount: Int?,
    private val video: Boolean?,
    @SerializedName(value = "vote_average")
    private val voteAverage: Float?
) {
    fun toValueObject(): Movie = Movie(
        id = id ?: throw NullPointerException("id must not be null"),
        title = title ?: "",
        voteAverage = voteAverage?.toDouble() ?: 5.0,
        posterPath = "https://image.tmdb.org/t/p/w500" + posterPath // TODO: use BuildConfigWrapper
    )

    fun toDbo() = NowPlayingEntity(
        posterPath = this.posterPath ?: "",
        adult = this.adult ?: false,
        overview = this.overview ?: "",
        releaseDate = this.releaseDate ?: "",
        id = this.id ?: 1,
        originalTitle = this.originalTitle ?: "",
        originalLanguage = this.originalLanguage ?: "",
        title = this.title ?: "",
        backdropPath = this.backdropPath ?: "",
        popularity = this.popularity ?: 5f,
        voteCount = this.voteCount ?: 5,
        video = this.video ?: false,
        voteAverage = this.voteAverage ?: 5f
    )
}