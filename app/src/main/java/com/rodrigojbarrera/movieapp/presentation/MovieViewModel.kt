package com.rodrigojbarrera.movieapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.rodrigojbarrera.movieapp.core.Resource
import com.rodrigojbarrera.movieapp.repository.MovieRepository
import kotlinx.coroutines.Dispatchers

class MovieViewModel(private val repo : MovieRepository) : ViewModel() {

    //Tareas de segundo plano IO
    fun fetchMainScreenMovies() = liveData(Dispatchers.IO){
        emit(Resource.Loading())
        try {
            emit(Resource.Succes(Triple(repo.getUpcomingMovie(), repo.getTopRateMovie(), repo.getPopularMovie())))
        } catch (e : Exception){
            emit(Resource.Failure(e))
        }
    }

}

class MoviewViewModelFactory(private val repo: MovieRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(MovieRepository::class.java).newInstance(repo)
    }
}