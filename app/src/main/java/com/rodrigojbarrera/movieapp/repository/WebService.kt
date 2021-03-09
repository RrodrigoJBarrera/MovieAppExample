package com.rodrigojbarrera.movieapp.repository

import com.google.gson.GsonBuilder
import com.rodrigojbarrera.movieapp.application.AppConstants
import com.rodrigojbarrera.movieapp.data.model.MovieList
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {

    @GET("movie/upcoming")
    suspend fun getUpcomingMovie(@Query("api_key") apiKey: String): MovieList

    @GET("movie/top_rated")
    suspend fun getTopRateMovie(@Query("api_key") apiKey: String): MovieList

    @GET("movie/popular")
    suspend fun getPopularMovie(@Query("api_key") apiKey: String): MovieList
}

object RetrofitClient{

    //lazy ejecuta cuando se lo necesita (en este caso WebService), no antes.
    val webService by lazy {
        Retrofit.Builder()
                .baseUrl(AppConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build().create(WebService::class.java)
    }
}