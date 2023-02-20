package com.e.tmdb.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.e.tmdb.database.dao.MovieDao
import com.e.tmdb.database.entity.*

@Database(
    entities = [DbMovie::class,
        DbCastMember::class,
        DbCrewMember::class,
        DbGenre::class,
        DbProductionCountry::class,
        MovieGenreCrossRef::class,
        MovieCountryCrossRef::class,
        MovieCrewCrossRef::class,
        MovieCastCrossRef::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}
