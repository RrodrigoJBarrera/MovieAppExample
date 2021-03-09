package com.rodrigojbarrera.movieapp.data.remote

import com.rodrigojbarrera.movieapp.application.AppConstants
import com.rodrigojbarrera.movieapp.data.model.MovieList
import com.rodrigojbarrera.movieapp.repository.WebService

class RemoteMovieDataSource(private val webService: WebService) {

    suspend fun getUpcomingMovies(): MovieList = webService.getUpcomingMovie(AppConstants.API_KEY)

    suspend fun getTopRateMovies(): MovieList = webService.getTopRateMovie(AppConstants.API_KEY)

    suspend fun getPopularMovies(): MovieList = webService.getPopularMovie(AppConstants.API_KEY)

}