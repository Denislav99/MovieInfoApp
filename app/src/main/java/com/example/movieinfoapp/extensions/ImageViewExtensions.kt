package com.example.movieinfoapp.extensions

import android.widget.ImageView
import androidx.annotation.DrawableRes
import coil.load
import com.example.movieinfoapp.R

fun ImageView.loadImageFromUrl(
    imageUrl: String?,
    @DrawableRes placeholderResource: Int = R.drawable.ic_movies,
    @DrawableRes errorResource: Int = R.drawable.ic_error,
) {
    this.load(imageUrl) {
        placeholder(placeholderResource)
        error(errorResource)
    }
}