package com.example.themoviedpapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.themoviedpapp.Models.Movie


class MoviesAdapter(

) : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {




    private var movies: MutableList<Movie>? = null


    private val onMovieClick: (movie: Movie) -> Unit
        get() {

            TODO()
        }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {

        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    
    override fun getItemCount(): Int = movies!!.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        movies?.get(position)?.let { holder.bind(it) }
    }
    fun appendMovies(movies: List<Movie>) {
        this.movies?.addAll(movies)
        this.movies?.let {
            notifyItemRangeInserted(
                it.size,
                movies.size - 1
            )
        }
    }


    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val poster: ImageView = itemView.findViewById(R.id.item_movie_poster)

        fun bind(movie: Movie) {
            Glide.with(itemView)
                    // dohvacamo poster sa MoviesDB stranice
                .load("https://image.tmdb.org/t/p/w342${movie.posterPath}")
                .transform(CenterCrop())
                .into(poster)
            // listener for when a movie is clicked
            itemView.setOnClickListener { onMovieClick.invoke(movie) }
        }
    }



}

