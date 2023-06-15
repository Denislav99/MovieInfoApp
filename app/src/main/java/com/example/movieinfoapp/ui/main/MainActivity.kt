package com.example.movieinfoapp.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.movieinfoapp.R
import com.example.movieinfoapp.constants.*
import com.example.movieinfoapp.database.genre.GenreDataBase
import com.example.movieinfoapp.databinding.ActivityMainBinding
import com.example.movieinfoapp.network.genre.GenreApi
import com.example.movieinfoapp.network.genre.GenreLocalSource
import com.example.movieinfoapp.network.genre.GenreRemoteSource
import com.example.movieinfoapp.repository.genre.GenreRepository
import com.example.movieinfoapp.repository.movie.MovieRepository
import com.example.movieinfoapp.ui.details.ActivityDetailsScreen


class MainActivity : AppCompatActivity(), MovieAdapterCallBack {

    private val movieViewModel by lazy {
        ViewModelProvider(
            this,
            MovieViewModelFactory(MovieRepository())
        )[MovieViewModel::class.java]
    }

    private val genreViewModel by lazy {
        ViewModelProvider(
            this,
            GenreViewModelFactory(GenreRepository(GenreLocalSource(db), GenreRemoteSource(GenreApi)), application)
        )[GenreViewModel::class.java]
    }

    private lateinit var db: GenreDataBase
    private lateinit var binding: ActivityMainBinding
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        movieAdapter = MovieAdapter(this)

        with(binding.recyclerViewMovies) {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = movieAdapter
        }

        db = Room.databaseBuilder(
            this,
            GenreDataBase::class.java, "genre"
        ).build()

        observeGenres()
        observeMovies()
        transparentStatusBar()
    }

    private fun observeGenres() {
        genreViewModel.genres.observe(this) {
            movieViewModel.updateMoviesByGenres(it)
        }

        genreViewModel.updateGenresIfNeeded()
    }

    private fun observeMovies() {
        movieViewModel.movies.observe(this) { movies ->
            if (movies.isEmpty())
                noMoviesFoundToast()
            else
                movieAdapter.setMovies(movies)
        }
    }

    private fun noMoviesFoundToast() {
        Toast.makeText(
            this,
            getString(R.string.noMoviesFound),
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun transparentStatusBar() {
        window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            statusBarColor = ContextCompat.getColor(this@MainActivity, R.color.transparent)
        }
    }

    override fun openMovieDetails(movie: Movie) {
        startActivity(Intent(this, ActivityDetailsScreen::class.java).apply {
            putExtra(MOVIE_ID_KEY, movie.id)
            putExtra(TITLE_KEY, movie.title)
            putExtra(GENRE_KEY, movie.genreNames.joinToString { s -> s })
            putExtra(IMAGEURL_KEY, movie.imageUrl)
            putExtra(DESCRIPTION_KEY, movie.description)
        })
    }
}