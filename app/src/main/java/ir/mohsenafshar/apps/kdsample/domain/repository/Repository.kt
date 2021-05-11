package ir.mohsenafshar.apps.kdsample.domain.repository

import androidx.lifecycle.LiveData
import ir.mohsenafshar.apps.kdsample.domain.entity.movie.GetMovieDetail
import ir.mohsenafshar.apps.kdsample.domain.entity.DataModel
import ir.mohsenafshar.apps.kdsample.util.data.Resource

interface Repository {
    fun getTopRated(pageNo: Int): LiveData<Resource<List<DataModel>>>
    fun getDetail(id: Long): LiveData<Resource<GetMovieDetail>>
}