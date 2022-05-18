package com.example.movieapplication.di

import androidx.lifecycle.ViewModel
import co.zsmb.rainbowcake.dagger.ViewModelKey
import com.example.movieapplication.ui.detailedmovie.MoviesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MoviesViewModel::class)
    abstract fun bindMoviesViewModel(moviesViewModel: MoviesViewModel): ViewModel

}