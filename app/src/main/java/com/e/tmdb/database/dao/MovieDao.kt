package com.e.tmdb.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.e.tmdb.database.entity.*
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: DbMovie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCrewMember(crewMember: DbCrewMember)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieCrewCrossRef(crewCrossRef: MovieCrewCrossRef)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCastMember(castMember: DbCastMember)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieCastCrossRef(castCrossRef: MovieCastCrossRef)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGenre(genre: DbGenre)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieGenreCrossRef(genreCrossRef: MovieGenreCrossRef)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProductionCountry(productionCountry: DbProductionCountry)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieCountryCrossRef(countryCrossRef: MovieCountryCrossRef)

    @Query("SELECT movie_id, title, poster_path, overview,is_favourite  FROM movie")
    fun getFavouriteMovies(): Flow<List<DbMovieTuple>>

    @Query("SELECT * FROM movie WHERE movie_id=:movieId")
    fun getMovieDetails(movieId: Int): Flow<DbMovieDetails>

    //DELETE
    @Query("DELETE FROM movie WHERE movie_id = :movieId")
    fun deleteMovie(movieId: Int)

}
