package com.e.tmdb.models.movieDetails

import com.e.tmdb.database.entity.DbProductionCountry

data class ProductionCountry(
    val iso: String,
    val name: String
)

fun ProductionCountryResponse.toProductionCompany() = ProductionCountry(
    iso, name
)

fun ProductionCountry.toDbProductionCountry() = DbProductionCountry(
    iso, name
)

fun DbProductionCountry.toProductionCompany() = ProductionCountry(
    countryId, countryName
)
