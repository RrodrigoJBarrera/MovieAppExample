package com.rodrigojbarrera.movieapp.repository

import com.rodrigojbarrera.movieapp.data.model.MovieList

interface MovieRepository {

    suspend fun getUpcomingMovie(): MovieList
    suspend fun getTopRateMovie(): MovieList
    suspend fun getPopularMovie(): MovieList
}