package com.rodrigojbarrera.movieapp.ui.movie

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import com.rodrigojbarrera.movieapp.R
import com.rodrigojbarrera.movieapp.core.Resource
import com.rodrigojbarrera.movieapp.data.local.AppDataBase
import com.rodrigojbarrera.movieapp.data.local.LocalMovieDataSource
import com.rodrigojbarrera.movieapp.data.model.Movie
import com.rodrigojbarrera.movieapp.data.remote.RemoteMovieDataSource
import com.rodrigojbarrera.movieapp.databinding.FragmentMovieBinding
import com.rodrigojbarrera.movieapp.presentation.MovieViewModel
import com.rodrigojbarrera.movieapp.presentation.MoviewViewModelFactory
import com.rodrigojbarrera.movieapp.repository.MovieRepositoryImpl
import com.rodrigojbarrera.movieapp.repository.RetrofitClient
import com.rodrigojbarrera.movieapp.ui.movie.adapters.MovieAdapter
import com.rodrigojbarrera.movieapp.ui.movie.adapters.concat.PopularConcatAdapter
import com.rodrigojbarrera.movieapp.ui.movie.adapters.concat.TopRatedConcatAdapter
import com.rodrigojbarrera.movieapp.ui.movie.adapters.concat.UpcomingConcatAdapter

class MovieFragment : Fragment(R.layout.fragment_movie), MovieAdapter.OnMovieClickListener {

    private lateinit var binding: FragmentMovieBinding

    private val viewModel by viewModels<MovieViewModel> {
        MoviewViewModelFactory(
            MovieRepositoryImpl(
                RemoteMovieDataSource(RetrofitClient.webService),
                LocalMovieDataSource(AppDataBase.getDataBase(requireContext()).movieDao())
            )
        )
    }

    private lateinit var concatAdapter: ConcatAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentMovieBinding.bind(view)

        concatAdapter = ConcatAdapter()

        viewModel.fetchMainScreenMovies().observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Succes -> {
                    binding.progressBar.visibility = View.GONE
                    concatAdapter.apply {
                        addAdapter(
                            0,
                            UpcomingConcatAdapter(
                                MovieAdapter(
                                    result.data.first.results,
                                    this@MovieFragment
                                )
                            )
                        )
                        addAdapter(
                            1,
                            TopRatedConcatAdapter(
                                MovieAdapter(
                                    result.data.second.results,
                                    this@MovieFragment
                                )
                            )
                        )
                        addAdapter(
                            2,
                            PopularConcatAdapter(
                                MovieAdapter(
                                    result.data.third.results,
                                    this@MovieFragment
                                )
                            )
                        )
                    }

                    binding.rvMovies.adapter = concatAdapter

                }
                is Resource.Failure -> {
                    Log.d("LiveData", "${result.exception}")
                }
            }
        })

    }

    override fun onMovieClick(movie: Movie) {
        val action = MovieFragmentDirections.actionMovieFragmentToMovieDetailFragment(
            movie.poster_path,
            movie.backdrop_path,
            movie.vote_average.toFloat(),
            movie.vote_count,
            movie.overview,
            movie.title,
            movie.original_language,
            movie.release_date
        )
        findNavController().navigate(action)
    }
}