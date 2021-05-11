package ir.mohsenafshar.apps.kdsample.data.remote

import androidx.lifecycle.LiveData
import ir.mohsenafshar.apps.kdsample.data.DataSource
import ir.mohsenafshar.apps.kdsample.domain.entity.movie.GetMovieDetail
import ir.mohsenafshar.apps.kdsample.data.remote.network.route.Api
import ir.mohsenafshar.apps.kdsample.domain.entity.DataModel
import ir.mohsenafshar.apps.kdsample.util.data.*


class RemoteDataSourceImpl(private val api: Api) : DataSource {
    override fun getTopRated(pageNo: Int): LiveData<Resource<List<DataModel>>> {
        return object : ListResultHandler<DataModel>() {
            override fun createCall(): LiveData<ApiListResponse<DataModel>> {
                return api.getTopRated(pageNo)
            }
        }.asLiveData()
    }

    override fun getDetail(id: Long): LiveData<Resource<GetMovieDetail>> {
        return object : ResultHandler<GetMovieDetail>() {
            override fun createCall(): LiveData<ApiResponse<GetMovieDetail>> {
                return api.getDetail(id)
            }
        }.asLiveData()
    }
}