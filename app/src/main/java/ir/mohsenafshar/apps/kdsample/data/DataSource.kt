package ir.mohsenafshar.apps.kdsample.data

import androidx.lifecycle.LiveData
import ir.mohsenafshar.apps.kdsample.domain.entity.DataModel
import ir.mohsenafshar.apps.kdsample.domain.entity.movie.MovieDetail
import ir.mohsenafshar.apps.kdsample.util.data.Resource

interface DataSource {
    fun getTopRated(pageNo: Int): LiveData<Resource<List<DataModel>>>
    fun getDetail(id: Long): LiveData<Resource<MovieDetail>>
}