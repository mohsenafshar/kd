package ir.mohsenafshar.apps.kdsample.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import ir.mohsenafshar.apps.kdsample.domain.dto.GetMovieParams
import ir.mohsenafshar.apps.kdsample.domain.entity.movie.MovieItem
import ir.mohsenafshar.apps.kdsample.domain.entity.movie.MovieDetail
import ir.mohsenafshar.apps.kdsample.domain.repository.MovieRepository
import ir.mohsenafshar.apps.kdsample.util.data.Resource

class MovieRepositoryImpl(private val remoteMovieDataSource: MovieDataSource) : MovieRepository {
    private val cachedDataList = arrayListOf<MovieItem>()

    override fun discoverLatestMovies(params: GetMovieParams): LiveData<Resource<List<MovieItem>>> {
        if (params.pageNo == 1) cachedDataList.clear()

        return remoteMovieDataSource.discoverLatestMovies(params).map { results ->
            if (results is Resource.Success) {
                cachedDataList.addAll(results.data!!)
                Resource.success(cachedDataList.map { item -> item.copy() })
            } else {
                results
            }
        }
    }

    override fun getDetail(id: Long): LiveData<Resource<MovieDetail>> {
        return remoteMovieDataSource.getDetail(id)
    }
}