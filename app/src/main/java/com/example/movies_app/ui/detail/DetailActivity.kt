package com.example.movies_app.ui.detail

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.example.movies_app.R
import com.example.movies_app.databinding.ActivityDetailBinding
import com.example.movies_app.model.Movie
import kotlin.math.log

class DetailActivity : AppCompatActivity() {
    //objeto que comparten todas las instancias de esta clase
    companion object {
        const val EXTRA_MOVIE = "DetailActivity:movie"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val movie = intent.getParcelableExtra<Movie>(EXTRA_MOVIE)
        movie?.let {
            title = it.title

            Glide
                .with(binding.root.context)
                .load("https://image.tmdb.org/t/p/w780${it.backdrop_path}")
                .into(binding.backdrop)
            binding.summary.text = it.overview + it.overview + it.overview + it.overview + it.overview + it.overview + it.overview + it.overview + it.overview + it.overview
            bindDetailInfo(binding.detailInfo, movie)

        }

        binding.addFavoriteMovies.setOnClickListener {
            Toast.makeText(this, "added to your favorites", Toast.LENGTH_LONG).show()
        }
    }

    private fun bindDetailInfo(detailInfo: TextView, movie: Movie) {
        detailInfo.text = buildSpannedString {
            appendInfo(R.string.original_language, movie.original_language)
            appendInfo(R.string.original_title, movie.original_title)
            appendInfo(R.string.release_date, movie.release_date)
            appendInfo(R.string.popularity, movie.popularity.toString())
            appendInfo(R.string.vote_avarage, movie.vote_average.toString())


        }
    }

    private fun SpannableStringBuilder.appendInfo(stringRes: Int, value: String) {
        bold {
            append(getString(stringRes))
            append(": ")
        }
        appendLine(value)
    }
}