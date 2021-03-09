package com.rodrigojbarrera.movieapp.ui.movie.adapters.concat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rodrigojbarrera.movieapp.core.BaseConcatViewHolder
import com.rodrigojbarrera.movieapp.core.BaseViewHolder
import com.rodrigojbarrera.movieapp.databinding.PopularMoviesRowBinding
import com.rodrigojbarrera.movieapp.ui.movie.adapters.MovieAdapter

class PopularConcatAdapter(private val moviesAdapter: MovieAdapter) : RecyclerView.Adapter<BaseConcatViewHolder<*>>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseConcatViewHolder<*> {
        val itemBinding = PopularMoviesRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ConcatViewHolder(itemBinding)
    }



    override fun onBindViewHolder(holder: BaseConcatViewHolder<*>, position: Int) {
        when(holder){
            is PopularConcatAdapter.ConcatViewHolder -> holder.bind(moviesAdapter)
        }
    }

    override fun getItemCount(): Int = 1

    private inner class ConcatViewHolder(val itemBinding: PopularMoviesRowBinding): BaseConcatViewHolder<MovieAdapter>(itemBinding.root) {
        override fun bind(adapter: MovieAdapter) {
            itemBinding.rvPopularMovies.adapter = adapter
        }
    }
}