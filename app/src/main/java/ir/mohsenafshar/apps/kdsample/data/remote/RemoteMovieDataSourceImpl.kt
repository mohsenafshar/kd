package ir.mohsenafshar.apps.kdsample.data.remote

import androidx.lifecycle.LiveData
import ir.mohsenafshar.apps.kdsample.data.MovieDataSource
import ir.mohsenafshar.apps.kdsample.data.remote.network.route.MovieApi
import ir.mohsenafshar.apps.kdsample.domain.dto.GetMovieParams
import ir.mohsenafshar.apps.kdsample.domain.entity.movie.MovieItem
import ir.mohsenafshar.apps.kdsample.domain.entity.movie.MovieDetail
import ir.mohsenafshar.apps.kdsample.util.data.*


class RemoteMovieDataSourceImpl(private val movieApi: MovieApi) : MovieDataSource {
    override fun discoverLatestMovies(params: GetMovieParams): LiveData<Resource<List<MovieItem>>> {
        return object : ListResultHandler<MovieItem>() {
            override fun createCall(): LiveData<ApiListResponse<MovieItem>> {
                return movieApi.discoverLatestMovies(params.pageNo, params.dateFilter)
            }
        }.asLiveData()
    }

    override fun getDetail(id: Long): LiveData<Resource<MovieDetail>> {
        return object : ResultHandler<MovieDetail>() {
            override fun createCall(): LiveData<ApiResponse<MovieDetail>> {
                return movieApi.getDetail(id)
            }
        }.asLiveData()
    }
}