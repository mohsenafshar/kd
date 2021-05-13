package ir.mohsenafshar.apps.kdsample.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import ir.mohsenafshar.apps.kdsample.domain.entity.movie.MovieDetail
import ir.mohsenafshar.apps.kdsample.domain.usecase.GetDetailMovieUseCase
import ir.mohsenafshar.apps.kdsample.util.data.Resource

class DetailViewModel(private val movieDetailUseCase: GetDetailMovieUseCase): ViewModel() {

    private val mDetail = MutableLiveData<Long>()
    val detail: LiveData<Resource<MovieDetail>> = Transformations.switchMap(mDetail) {
        movieDetailUseCase.invoke(it)
    }

    fun getDetail(id: Long) {
        mDetail.value = id
    }
}