package com.my.movies.data.dto

import com.google.gson.annotations.SerializedName
import com.my.movies.data.storage.dto.NowPlayingEntity
import com.my.movies.domain.Movie

internal data class MoviesResponse(
    private val page: Int?,
    private val results: List<MovieDto>?,
    private val maximum: String?,
    private val minimum: String?,
    @SerializedName(value = "total_pages")
    private val totalPages: Int?,
    @SerializedName(value = "total_results")
    private val totalResults: Int?
) {
    fun toValueObject(): List<Movie>? = results?.map { movieDto -> movieDto.toValueObject() }

    fun toDbo(): List<NowPlayingEntity> = results?.map { network -> network.toDbo() } ?: emptyList()
}