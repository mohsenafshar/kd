package ir.mohsenafshar.apps.kdsample.domain.usecase

import androidx.lifecycle.LiveData
import ir.mohsenafshar.apps.kdsample.domain.dto.GetMovieParams
import ir.mohsenafshar.apps.kdsample.domain.entity.movie.MovieItem
import ir.mohsenafshar.apps.kdsample.domain.repository.MovieRepository
import ir.mohsenafshar.apps.kdsample.util.data.Resource

class GetMovieListUseCase(private val movieRepository: MovieRepository) {
    fun invoke(params: GetMovieParams): LiveData<Resource<List<MovieItem>>> {
        return movieRepository.discoverLatestMovies(params)
    }
}