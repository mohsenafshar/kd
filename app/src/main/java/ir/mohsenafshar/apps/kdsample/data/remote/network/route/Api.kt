package ir.mohsenafshar.apps.kdsample.data.remote.network.route

import androidx.lifecycle.LiveData
import ir.mohsenafshar.apps.kdsample.domain.entity.movie.GetMovieDetail
import ir.mohsenafshar.apps.kdsample.domain.entity.DataModel
import ir.mohsenafshar.apps.kdsample.util.data.ApiListResponse
import ir.mohsenafshar.apps.kdsample.util.data.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    @GET("top_rated")
    fun getTopRated(@Query("page") pageNo: Int): LiveData<ApiListResponse<DataModel>>

    @GET("{id}")
    fun getDetail(@Path("id") id: Long): LiveData<ApiResponse<GetMovieDetail>>
}