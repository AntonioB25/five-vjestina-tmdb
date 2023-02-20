package com.e.tmdb.database.entity

import androidx.room.*

@Entity(tableName = "movie")
data class DbMovie(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "movie_id")
    val movieId: Int,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "poster_path")
    val posterPath: String,
    @ColumnInfo(name = "backdrop_path")
    val backdropPath: String?,
    @ColumnInfo(name = "original_language")
    val originalLanguage: String,
    @ColumnInfo(name = "overview")
    val overview: String,
    @ColumnInfo(name = "release_date")
    val releaseDate: String,
    @ColumnInfo(name = "runtime")
    val runtime: Int?,
    @ColumnInfo(name = "is_favourite")
    val isFavorite: Boolean,
)

data class DbMovieDetails(
    @Embedded val movie: DbMovie,
    @Relation(
        parentColumn = "movie_id",
        entityColumn = "crew_id",
        associateBy = Junction(MovieCrewCrossRef::class)
    )
    val crew: List<DbCrewMember>,
    @Relation(
        parentColumn = "movie_id",
        entityColumn = "cast_id",
        associateBy = Junction(MovieCastCrossRef::class)
    )
    val cast: List<DbCastMember>,
    @Relation(
        parentColumn = "movie_id",
        entityColumn = "genre_id",
        associateBy = Junction(MovieGenreCrossRef::class)
    )
    val genres: List<DbGenre>,
    @Relation(
        parentColumn = "movie_id",
        entityColumn = "country_id",
        associateBy = Junction(MovieCountryCrossRef::class)
    )
    val productionCountries: List<DbProductionCountry>
)


@Entity(primaryKeys = ["movie_id", "country_id"])
data class MovieCountryCrossRef(
    @ColumnInfo(name = "movie_id")
    val movieId: Int,
    @ColumnInfo(name = "country_id")
    val countryId: String
)

@Entity(primaryKeys = ["movie_id", "genre_id"])
data class MovieGenreCrossRef(
    @ColumnInfo(name = "movie_id")
    val movieId: Int,
    @ColumnInfo(name = "genre_id")
    val genreId: Int
)

@Entity(primaryKeys = ["movie_id", "cast_id"])
data class MovieCastCrossRef(
    @ColumnInfo(name = "movie_id")
    val movieId: Int,
    @ColumnInfo(name = "cast_id")
    val castId: Int
)

@Entity(primaryKeys = ["movie_id", "crew_id"])
data class MovieCrewCrossRef(
    @ColumnInfo(name = "movie_id")
    val movieId: Int,
    @ColumnInfo(name = "crew_id")
    val crewId: Int
)
