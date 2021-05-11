package ir.mohsenafshar.apps.kdsample.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import ir.mohsenafshar.apps.kdsample.domain.entity.DataModel
import ir.mohsenafshar.apps.kdsample.domain.usecase.GetModelListUseCase
import ir.mohsenafshar.apps.kdsample.util.data.Resource

class MainViewModel(private val useCase: GetModelListUseCase): ViewModel() {

    private val mTopRated = MutableLiveData<Int>()
    val topRated: LiveData<Resource<List<DataModel>>> = Transformations.switchMap(mTopRated) {
        useCase.invoke(it)
    }

    fun getTopRated(pageNo: Int) {
        mTopRated.value = pageNo
    }
}