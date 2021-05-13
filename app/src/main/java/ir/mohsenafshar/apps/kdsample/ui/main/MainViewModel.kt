package ir.mohsenafshar.apps.kdsample.ui.main

import androidx.lifecycle.*
import ir.mohsenafshar.apps.kdsample.domain.dto.GetMovieParams
import ir.mohsenafshar.apps.kdsample.domain.entity.movie.MovieItem
import ir.mohsenafshar.apps.kdsample.domain.usecase.GetModelListUseCase
import ir.mohsenafshar.apps.kdsample.util.data.Resource

class MainViewModel(private val useCase: GetModelListUseCase): ViewModel() {
    private val cachedDataList = arrayListOf<MovieItem>()

    private val mCurrentPageIndex: MutableLiveData<Int> = MutableLiveData(1)
    private val mQuery = MutableLiveData("")

    val movies: LiveData<Resource<List<MovieItem>>> = mCurrentPageIndex.switchMap { index ->
        useCase.invoke(GetMovieParams(index, mQuery.value))
    }

    fun setQuery(originalInput: String) {
        if (originalInput == mQuery.value) {
            return
        }
        cachedDataList.clear()
        mQuery.value = originalInput
        mCurrentPageIndex.value = 1
    }

    fun loadNextPage() {
        mCurrentPageIndex.value = mCurrentPageIndex.value!!.plus(1)
    }
}