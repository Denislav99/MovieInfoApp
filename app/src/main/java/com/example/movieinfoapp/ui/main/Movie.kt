package com.example.movieinfoapp.ui.main

data class Movie(
    val id: Long,
    val imageUrl: String,
    val genreNames: List<String>,
    val title: String,
    val description: String
)