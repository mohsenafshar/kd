package ir.mohsenafshar.apps.kdsample.data

import androidx.lifecycle.LiveData
import ir.mohsenafshar.apps.kdsample.domain.entity.movie.GetMovieDetail
import ir.mohsenafshar.apps.kdsample.domain.entity.DataModel
import ir.mohsenafshar.apps.kdsample.domain.repository.Repository
import ir.mohsenafshar.apps.kdsample.util.data.Resource

class RepositoryImpl(private val remoteDataSource: DataSource) : Repository {
    override fun getTopRated(pageNo: Int): LiveData<Resource<List<DataModel>>> {
        return remoteDataSource.getTopRated(pageNo)
    }

    override fun getDetail(id: Long): LiveData<Resource<GetMovieDetail>> {
        return remoteDataSource.getDetail(id)
    }
}