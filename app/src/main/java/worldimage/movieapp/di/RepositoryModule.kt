package worldimage.movieapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import worldimage.movieapp.movieList.data.repository.MovieListRepositoryImpl
import worldimage.movieapp.movieList.domain.repository.MovieListRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindsMovieListRepository(impl: MovieListRepositoryImpl): MovieListRepository
}