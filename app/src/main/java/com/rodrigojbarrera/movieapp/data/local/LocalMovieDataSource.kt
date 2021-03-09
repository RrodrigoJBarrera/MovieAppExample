package com.rodrigojbarrera.movieapp.data.local

import com.rodrigojbarrera.movieapp.application.AppConstants
import com.rodrigojbarrera.movieapp.data.model.MovieEntity
import com.rodrigojbarrera.movieapp.data.model.MovieList
import com.rodrigojbarrera.movieapp.data.model.toMovieList

class LocalMovieDataSource(private val movieDao: MovieDao) {


    suspend fun getUpcomingMovies(): MovieList {

        return movieDao.getAllMovies().filter { it.movie_type == "upcoming" }.toMovieList()
    }

    suspend fun getTopRateMovies(): MovieList {

        return movieDao.getAllMovies().filter { it.movie_type == "toprated" }.toMovieList()
    }

    suspend fun getPopularMovies(): MovieList {

        return movieDao.getAllMovies().filter { it.movie_type == "popular" }.toMovieList()
    }


    suspend fun saveMovie(movieEntity: MovieEntity) {
        movieDao.saveMovie(movieEntity)
    }
}