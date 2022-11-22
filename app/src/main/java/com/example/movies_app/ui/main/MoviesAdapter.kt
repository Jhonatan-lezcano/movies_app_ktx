package com.example.movies_app.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movies_app.databinding.ViewMovieItemBinding
import com.example.movies_app.model.Movie


class MoviesAdapter(
    var movies: List<Movie>,
    private val movieClickedListener: (movie: Movie) -> Unit
) :
    RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    class ViewHolder(private val binding: ViewMovieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.title.text = movie.title
            Glide
                .with(binding.root.context)
                .load("https://image.tmdb.org/t/p/w185${movie.poster_path}")
                .into(binding.cover)
        }
    }

    // Create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ViewMovieItemBinding.inflate(
            LayoutInflater
                .from(parent.context), parent, false
        )

        return ViewHolder(view)
    }

    // Replace the content of a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
        holder.itemView.setOnClickListener { movieClickedListener(movie) }
    }

    //Return the size of you dataset
    override fun getItemCount(): Int = movies.size
}