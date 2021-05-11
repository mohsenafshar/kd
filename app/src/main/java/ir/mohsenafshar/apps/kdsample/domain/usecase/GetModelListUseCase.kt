package ir.mohsenafshar.apps.kdsample.domain.usecase

import androidx.lifecycle.LiveData
import ir.mohsenafshar.apps.kdsample.domain.entity.DataModel
import ir.mohsenafshar.apps.kdsample.domain.repository.Repository
import ir.mohsenafshar.apps.kdsample.util.data.Resource

class GetModelListUseCase(private val repository: Repository) {
    fun invoke(pageNo: Int): LiveData<Resource<List<DataModel>>> {
        return repository.getTopRated(pageNo)
    }
}