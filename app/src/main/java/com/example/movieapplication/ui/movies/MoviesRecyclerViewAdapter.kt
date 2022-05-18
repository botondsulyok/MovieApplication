package com.example.movieapplication.ui.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapplication.R
import com.example.movieapplication.ui.movies.models.UiMovie
import kotlinx.android.synthetic.main.item_movie.view.*

class MoviesRecyclerViewAdapter : ListAdapter<UiMovie, MoviesRecyclerViewAdapter.MovieViewHolder>(MoviesDiffCallback) {

    var onMovieClicked: ((UiMovie) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(parent.inflate(R.layout.item_movie, false))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

   inner class MovieViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {

       fun bind(movie: UiMovie) {
           itemView.titleText.text = movie.title
           itemView.budgetText.apply {
               text = movie.budget.toString()
               isVisible = movie.budget != null
           }

           // todo load image with glide

           itemView.setOnClickListener {
               onMovieClicked?.invoke(movie)
           }
       }
   }

    object MoviesDiffCallback: DiffUtil.ItemCallback<UiMovie>() {
        override fun areItemsTheSame(oldItem: UiMovie, newItem: UiMovie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UiMovie, newItem: UiMovie): Boolean {
            return oldItem == newItem
        }
    }

    private fun ViewGroup.inflate(@LayoutRes layoutId: Int, attachToRoot: Boolean): View {
        return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
    }
}