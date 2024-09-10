package worldimage.movieapp.movieList.presentation

import worldimage.movieapp.movieList.domain.model.Movie

data class MovieListState(
    val isLoading: Boolean = false,
    val movieList: List<Movie> = emptyList()
)