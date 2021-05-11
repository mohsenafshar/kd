package ir.mohsenafshar.apps.kdsample.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import ir.mohsenafshar.apps.kdsample.domain.entity.movie.GetMovieDetail
import ir.mohsenafshar.apps.kdsample.domain.usecase.GetDetailUseCase
import ir.mohsenafshar.apps.kdsample.util.data.Resource

class DetailViewModel(private val useCase: GetDetailUseCase): ViewModel() {

    private val mDetail = MutableLiveData<Long>()
    val detail: LiveData<Resource<GetMovieDetail>> = Transformations.switchMap(mDetail) {
        useCase.invoke(it)
    }

    fun getDetail(id: Long) {
        mDetail.value = id
    }
}