package ir.mohsenafshar.apps.kdsample.domain.usecase

import androidx.lifecycle.LiveData
import ir.mohsenafshar.apps.kdsample.domain.entity.movie.MovieDetail
import ir.mohsenafshar.apps.kdsample.domain.repository.Repository
import ir.mohsenafshar.apps.kdsample.util.data.Resource

class GetDetailUseCase(private val repository: Repository) {
    fun invoke(id: Long): LiveData<Resource<MovieDetail>> {
        return repository.getDetail(id)
    }
}