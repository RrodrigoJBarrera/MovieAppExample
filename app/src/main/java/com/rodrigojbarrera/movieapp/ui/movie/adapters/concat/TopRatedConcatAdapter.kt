package com.rodrigojbarrera.movieapp.ui.movie.adapters.concat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rodrigojbarrera.movieapp.core.BaseConcatViewHolder
import com.rodrigojbarrera.movieapp.databinding.PopularMoviesRowBinding
import com.rodrigojbarrera.movieapp.databinding.TopMoviesRowBinding
import com.rodrigojbarrera.movieapp.ui.movie.adapters.MovieAdapter

class TopRatedConcatAdapter (private val moviesAdapter: MovieAdapter) : RecyclerView.Adapter<BaseConcatViewHolder<*>>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseConcatViewHolder<*> {
        val itemBinding = TopMoviesRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ConcatViewHolder(itemBinding)
    }



    override fun onBindViewHolder(holder: BaseConcatViewHolder<*>, position: Int) {
        when(holder){
            is TopRatedConcatAdapter.ConcatViewHolder -> holder.bind(moviesAdapter)
        }
    }

    override fun getItemCount(): Int = 1

    private inner class ConcatViewHolder(val itemBinding: TopMoviesRowBinding): BaseConcatViewHolder<MovieAdapter>(itemBinding.root) {
        override fun bind(adapter: MovieAdapter) {
            itemBinding.rvTopMovies.adapter = adapter
        }
    }
}