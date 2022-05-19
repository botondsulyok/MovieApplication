package com.example.movieapplication.ui.movies

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import co.zsmb.rainbowcake.base.OneShotEvent
import co.zsmb.rainbowcake.base.RainbowCakeFragment
import co.zsmb.rainbowcake.dagger.getViewModelFromFactory
import com.afollestad.materialdialogs.LayoutMode
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.bottomsheets.BottomSheet
import com.afollestad.materialdialogs.customview.customView
import com.bumptech.glide.Glide
import com.example.movieapplication.R
import com.example.movieapplication.ui.extensions.showToast
import com.example.movieapplication.ui.movies.models.UiMovie
import kotlinx.android.synthetic.main.fragment_movies.*
import kotlinx.android.synthetic.main.item_movie.view.*
import kotlinx.android.synthetic.main.view_details_dialog.*

class MoviesFragment : RainbowCakeFragment<MoviesViewState, MoviesViewModel>() {

    override fun provideViewModel() = getViewModelFromFactory()
    override fun getViewResource() = R.layout.fragment_movies

    private lateinit var moviesRecyclerViewAdapter: MoviesRecyclerViewAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListeners()
        setupRecyclerView()

        viewModel.loadMovies()
    }

    private fun setListeners() {
        moviesRefreshLayout.setOnRefreshListener {
            viewModel.updateMovies()
        }
    }

    private fun setupRecyclerView() {
        moviesRecyclerViewAdapter = MoviesRecyclerViewAdapter(requireContext(), ::showDetailsDialog)
        moviesList.adapter = moviesRecyclerViewAdapter
    }

    private fun showDetailsDialog(movie: UiMovie) {
        MaterialDialog(requireContext(), BottomSheet(LayoutMode.WRAP_CONTENT)).show {
            customView(R.layout.view_details_dialog, scrollable = true, horizontalPadding = true)
            detailedMovieTitleText.text = movie.title
            if (movie.voteAverage == null) {
                detailedMovieRatingBar.isVisible = false
            } else {
                detailedMovieRatingBar.rating = movie.voteAverage.toFloat()
            }
            Glide
                .with(this@MoviesFragment.requireContext())
                .load(movie.imageUrl)
                .onlyRetrieveFromCache(true)
                .placeholder(R.drawable.ic_baseline_image_24)
                .into(detailedMovieImage)
        }
    }

    override fun render(viewState: MoviesViewState) {
        when (viewState) {
            is Loading -> showLoading()
            is Error -> showError(viewState.errorMessage)
            is MoviesLoaded -> showMovies(viewState.movies)
        }
    }

    override fun onEvent(event: OneShotEvent) {
        when (event) {
            is MoviesViewModel.FailedToUpdateEvent -> showToast(event.message)
        }
    }

    private fun showLoading() {
        if (moviesRefreshLayout.isRefreshing.not()) moviesProgressBar.isVisible = true
    }

    private fun showError(message: String?) {
        moviesProgressBar.isVisible = false
        moviesRefreshLayout.isRefreshing = false
        showToast(message)
    }

    private fun showMovies(movies: List<UiMovie>) {
        moviesProgressBar.isVisible = false
        moviesRefreshLayout.isRefreshing = false
        moviesRecyclerViewAdapter.submitList(movies)
    }
}