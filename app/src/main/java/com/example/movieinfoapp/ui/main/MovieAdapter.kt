package com.example.movieinfoapp.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieinfoapp.databinding.ActivityMovieListCellBinding

class MovieAdapter(private val movieAdapterCallBack: MovieAdapterCallBack) : RecyclerView.Adapter<MovieViewHolder>() {
    private var movies = listOf<Movie>()

    fun setMovies(movies: List<Movie>) {
        this.movies = movies
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder =
        MovieViewHolder(
            ActivityMovieListCellBinding
                .inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindMovie(movies[position], movieAdapterCallBack)
    }

    override fun getItemCount(): Int = movies.size
}