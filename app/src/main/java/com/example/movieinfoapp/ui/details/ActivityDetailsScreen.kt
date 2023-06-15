package com.example.movieinfoapp.ui.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.movieinfoapp.R
import com.example.movieinfoapp.constants.*
import com.example.movieinfoapp.databinding.ActivityDetailsScreenBinding
import com.example.movieinfoapp.extensions.loadImageFromUrl
import kotlin.properties.Delegates

class ActivityDetailsScreen: AppCompatActivity() {

    private lateinit var binding: ActivityDetailsScreenBinding
    private var movieId by Delegates.notNull<Long>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movieId = intent.getLongExtra(MOVIE_ID_KEY, -1)
        if (movieId <= -1) {
            Toast.makeText(this, R.string.commonErrorMessage, Toast.LENGTH_LONG).show()
            finish()
            return
        }

        this.movieId = movieId
        receiveMovieDetails()

        transparentStatusBar()
    }

    private fun receiveMovieDetails() {
        with(binding) {
            if (intent.getStringExtra(IMAGEURL_KEY) != null) {
                imageView.loadImageFromUrl(intent.getStringExtra(IMAGEURL_KEY))
            }
            movieDetailsTitle.text = intent.getStringExtra(TITLE_KEY) ?: EMPTY_STRING
            movieDetailsDescription.text = intent.getStringExtra(DESCRIPTION_KEY) ?: EMPTY_STRING
            movieGenreTextView.text = intent.getStringExtra(GENRE_KEY) ?: EMPTY_STRING
        }
    }

    private fun transparentStatusBar() {
        window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            statusBarColor = ContextCompat.getColor(this@ActivityDetailsScreen, R.color.transparent)
        }
    }
}