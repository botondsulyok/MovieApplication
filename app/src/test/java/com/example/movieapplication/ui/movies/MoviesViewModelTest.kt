package com.example.movieapplication.ui.movies

import co.zsmb.rainbowcake.test.assertObserved
import co.zsmb.rainbowcake.test.base.ViewModelTest
import co.zsmb.rainbowcake.test.observeStateAndEvents
import com.example.movieapplication.data.ResultFailure
import com.example.movieapplication.data.ResultSuccess
import com.example.movieapplication.ui.movies.MoviesViewModel.FailedToUpdateEvent
import com.example.movieapplication.ui.movies.models.UiMovie
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import kotlinx.coroutines.test.runTest
import org.junit.Test

@ExperimentalCoroutinesApi
class MoviesViewModelTest : ViewModelTest() {

    private lateinit var viewModel: MoviesViewModel

    private var moviesPresenter: MoviesPresenter = mockk()

    @Before
    fun setup() {
        clearAllMocks()
        viewModel = MoviesViewModel(moviesPresenter)
    }

    @Test
    fun testLoadMoviesSuccess() = runTest {
        // Given
        coEvery { moviesPresenter.getMoviesFromCache() } returns ResultSuccess(MOVIE_MOCK_LIST_CACHE)
        coEvery { moviesPresenter.getMoviesFromNetwork() } returns ResultSuccess(MOVIE_MOCK_LIST)
        coEvery { moviesPresenter.cacheMovies(MOVIE_MOCK_LIST) } just Runs

        //When, Then
        viewModel.observeStateAndEvents { stateObserver, _ ->
            viewModel.loadMovies()
            stateObserver.assertObserved(Loading, MoviesLoaded(MOVIE_MOCK_LIST_CACHE), Loading, MoviesLoaded(MOVIE_MOCK_LIST))
        }
        coVerify(exactly = 1) { moviesPresenter.getMoviesFromCache() }
        coVerify(exactly = 1) { moviesPresenter.getMoviesFromNetwork() }
        coVerify(exactly = 1) { moviesPresenter.cacheMovies(MOVIE_MOCK_LIST) }
    }

    @Test
    fun testLoadMoviesNetworkCallFailed() = runTest {
        // Given
        coEvery { moviesPresenter.getMoviesFromCache() } returns ResultSuccess(MOVIE_MOCK_LIST_CACHE)
        coEvery { moviesPresenter.getMoviesFromNetwork() } returns ResultFailure("")
        coEvery { moviesPresenter.cacheMovies(MOVIE_MOCK_LIST) } just Runs

        //When, Then
        viewModel.observeStateAndEvents { stateObserver, _, queuedOneShotEvent ->
            viewModel.loadMovies()
            stateObserver.assertObserved(Loading, MoviesLoaded(MOVIE_MOCK_LIST_CACHE), Loading)
            queuedOneShotEvent.assertObserved(FailedToUpdateEvent())
        }
        coVerify(exactly = 1) { moviesPresenter.getMoviesFromCache() }
        coVerify(exactly = 1) { moviesPresenter.getMoviesFromNetwork() }
        coVerify(exactly = 0) { moviesPresenter.cacheMovies(MOVIE_MOCK_LIST) }
    }

    companion object {
        private val MOVIE_MOCK = UiMovie(0, "title", 10, "", "")
        private val MOVIE_MOCK_LIST_CACHE = listOf(MOVIE_MOCK)
        private val MOVIE_MOCK_LIST = listOf(MOVIE_MOCK.copy())
    }
}