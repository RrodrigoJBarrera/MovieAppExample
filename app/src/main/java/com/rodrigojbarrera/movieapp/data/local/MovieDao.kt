package com.rodrigojbarrera.movieapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rodrigojbarrera.movieapp.data.model.Movie
import com.rodrigojbarrera.movieapp.data.model.MovieEntity

@Dao
interface MovieDao {

    @Query("SELECT * FROM movieentity")
    suspend fun getAllMovies(): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovie (movie: MovieEntity)
}
