package com.rodrigojbarrera.movieapp.repository

import com.rodrigojbarrera.movieapp.core.InternetCheck
import com.rodrigojbarrera.movieapp.data.local.LocalMovieDataSource
import com.rodrigojbarrera.movieapp.data.model.MovieList
import com.rodrigojbarrera.movieapp.data.model.toMovieEntity
import com.rodrigojbarrera.movieapp.data.remote.RemoteMovieDataSource

class MovieRepositoryImpl(
    private val dataSourceRemote: RemoteMovieDataSource,
    private val dataSourceLocal: LocalMovieDataSource
) : MovieRepository {

    override suspend fun getUpcomingMovie(): MovieList {

        return if (InternetCheck.isNetworkAvailable()) {
            dataSourceRemote.getUpcomingMovies().results.forEach { movie ->
                dataSourceLocal.saveMovie(movie.toMovieEntity("upcoming"))
            }
            dataSourceLocal.getUpcomingMovies()
        } else {
            dataSourceLocal.getUpcomingMovies()
        }


    }

    override suspend fun getTopRateMovie(): MovieList {

        return if (InternetCheck.isNetworkAvailable()) {
            dataSourceRemote.getTopRateMovies().results.forEach { movie ->
                dataSourceLocal.saveMovie(movie.toMovieEntity("toprated"))
            }
            dataSourceLocal.getTopRateMovies()
        } else {
            dataSourceLocal.getTopRateMovies()
        }

    }

    override suspend fun getPopularMovie(): MovieList {

        return if (InternetCheck.isNetworkAvailable()) {
            dataSourceRemote.getPopularMovies().results.forEach { movie ->
                dataSourceLocal.saveMovie(movie.toMovieEntity("popular"))
            }
            dataSourceLocal.getPopularMovies()
        } else {
            dataSourceLocal.getPopularMovies()
        }

    }
}