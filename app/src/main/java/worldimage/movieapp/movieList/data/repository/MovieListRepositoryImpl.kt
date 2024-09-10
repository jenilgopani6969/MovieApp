package worldimage.movieapp.movieList.data.repository

import coil.network.HttpException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import worldimage.movieapp.movieList.data.remote.MovieListApi
import worldimage.movieapp.movieList.data.remote.dto.toMovie
import worldimage.movieapp.movieList.domain.model.Movie
import worldimage.movieapp.movieList.domain.repository.MovieListRepository
import worldimage.movieapp.util.Resource
import javax.inject.Inject

class MovieListRepositoryImpl @Inject constructor(
    private val movieListApi: MovieListApi
): MovieListRepository {
    override suspend fun getMovieList(category: String, page: Int): Flow<Resource<List<Movie>>> {
        return flow {
            emit(Resource.Loading(true))
            try {
                val movieListFromApi = movieListApi.getMovieList(
                    category = category,
                    page = 1
                )
                emit(Resource.Success(
                    data = movieListFromApi.results.map { movieDto ->
                        movieDto.toMovie()
                    }
                ))
            } catch (e: IOException) {
                emit(Resource.Error(message = "Error While Connecting to Internet!!"))
            } catch (e: HttpException) {
                emit(Resource.Error(message = "Unknown Error!!"))
            } catch (e: Exception) {
                emit(Resource.Error(message = e.message ?: "Unknown Exception!!"))
            }
            emit(Resource.Loading(false))
            return@flow
        }
    }
}