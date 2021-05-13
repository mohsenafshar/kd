package ir.mohsenafshar.apps.kdsample.domain.usecase

import androidx.lifecycle.LiveData
import ir.mohsenafshar.apps.kdsample.domain.entity.movie.MovieDetail
import ir.mohsenafshar.apps.kdsample.domain.repository.MovieRepository
import ir.mohsenafshar.apps.kdsample.util.data.Resource

class GetDetailMovieUseCase(private val movieRepository: MovieRepository) {
    fun invoke(id: Long): LiveData<Resource<MovieDetail>> {
        return movieRepository.getDetail(id)
    }
}