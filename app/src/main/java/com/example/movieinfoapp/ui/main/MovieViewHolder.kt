package com.example.movieinfoapp.ui.main

import androidx.recyclerview.widget.RecyclerView
import com.example.movieinfoapp.databinding.ActivityMovieListCellBinding
import com.example.movieinfoapp.extensions.loadImageFromUrl

class MovieViewHolder(
    private val binding: ActivityMovieListCellBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bindMovie(movie: Movie, movieAdapterCallBack: MovieAdapterCallBack) {
        with(binding) {
            moviePosterImageView.loadImageFromUrl(movie.imageUrl)
            movieTitleTextView.text = movie.title
            movieGenreTextView.text = movie.genreNames.joinToString { s -> s }
            movieDescriptionTextView.text = movie.description
            cardMovie.setOnClickListener{
                movieAdapterCallBack.openMovieDetails(movie)
            }
        }
    }
}