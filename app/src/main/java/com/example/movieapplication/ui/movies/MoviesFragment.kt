package com.example.movieapplication.ui.movies

import android.os.Bundle
import android.view.View
import co.zsmb.rainbowcake.base.RainbowCakeFragment
import co.zsmb.rainbowcake.dagger.getViewModelFromFactory
import com.example.movieapplication.R
import com.example.movieapplication.ui.movies.models.UiMovie
import kotlinx.android.synthetic.main.fragment_movies.*

class MoviesFragment : RainbowCakeFragment<MoviesViewState, MoviesViewModel>() {

    override fun provideViewModel() = getViewModelFromFactory()
    override fun getViewResource() = R.layout.fragment_movies

    private lateinit var moviesRecyclerViewAdapter: MoviesRecyclerViewAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

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
            // todo
        }
        // todo remove this
        moviesRecyclerViewAdapter.submitList(
            listOf(
                UiMovie("1", "Title1", "1500"),
                UiMovie("2", "2", "2")
            )
        )
    }
}