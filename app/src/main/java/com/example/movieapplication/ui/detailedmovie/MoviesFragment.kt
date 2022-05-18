package com.example.movieapplication.ui.detailedmovie

import android.os.Bundle
import android.view.View
import co.zsmb.rainbowcake.base.RainbowCakeFragment
import co.zsmb.rainbowcake.dagger.getViewModelFromFactory
import com.example.movieapplication.R

class MoviesFragment : RainbowCakeFragment<MoviesViewState, MoviesViewModel>() {

    override fun provideViewModel() = getViewModelFromFactory()
    override fun getViewResource() = R.layout.fragment_movies

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loadMovies()
    }

    override fun render(viewState: MoviesViewState) {
        when (viewState) {
            // todo
        }
    }
}