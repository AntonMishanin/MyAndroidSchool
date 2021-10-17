package com.my.movie.dto

import com.google.gson.annotations.SerializedName

data class ProductionCountryDto(
    @SerializedName(value = "iso_3166_1")
    val iso_3166_1: String?,
    val name: String?
)