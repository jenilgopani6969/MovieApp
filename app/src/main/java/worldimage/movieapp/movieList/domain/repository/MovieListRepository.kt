package worldimage.movieapp.movieList.domain.repository

import kotlinx.coroutines.flow.Flow
import worldimage.movieapp.movieList.domain.model.Movie
import worldimage.movieapp.util.Resource

interface MovieListRepository {
    suspend fun getMovieList(
        category: String,
        page: Int
    ): Flow<Resource<List<Movie>>>
}