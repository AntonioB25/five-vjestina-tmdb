package com.e.tmdb.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "production_company")
data class DbProductionCountry(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "country_id")
    val countryId: String,
    @ColumnInfo(name = "country_name")
    val countryName: String
)


