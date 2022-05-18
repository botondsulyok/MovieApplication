package com.example.movieapplication.ui.movies

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import co.zsmb.rainbowcake.base.OneShotEvent
import co.zsmb.rainbowcake.base.RainbowCakeFragment
import co.zsmb.rainbowcake.dagger.getViewModelFromFactory
import com.example.movieapplication.R
import com.example.movieapplication.ui.extensions.showToast
import com.example.movieapplication.ui.movies.models.UiMovie
import kotlinx.android.synthetic.main.fragment_movies.*

class MoviesFragment : RainbowCakeFragment<MoviesViewState, MoviesViewModel>() {

    override fun provideViewModel() = getViewModelFromFactory()
    override fun getViewResource() = R.layout.fragment_movies

    private lateinit var moviesRecyclerViewAdapter: MoviesRecyclerViewAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        Thread.sleep(2000)
        viewModel.loadMovies()
    }

    private fun setupRecyclerView() {
        moviesRecyclerViewAdapter = MoviesRecyclerViewAdapter().apply {
            onMovieClicked = { }
        }
        moviesList.adapter = moviesRecyclerViewAdapter
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
        moviesProgressBar.isVisible = true
    }

    private fun showError(message: String?) {
        showToast(message)
        moviesProgressBar.isVisible = false
    }

    private fun showMovies(movies: List<UiMovie>) {
        moviesProgressBar.isVisible = false
        moviesRecyclerViewAdapter.submitList(movies)
    }
}