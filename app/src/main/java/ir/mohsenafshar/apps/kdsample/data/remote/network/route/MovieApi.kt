package ir.mohsenafshar.apps.kdsample.data.remote.network.route

import androidx.lifecycle.LiveData
import ir.mohsenafshar.apps.kdsample.domain.entity.movie.MovieItem
import ir.mohsenafshar.apps.kdsample.domain.entity.movie.MovieDetail
import ir.mohsenafshar.apps.kdsample.util.data.ApiListResponse
import ir.mohsenafshar.apps.kdsample.util.data.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/top_rated")
    fun getTopRated(@Query("page") pageNo: Int): LiveData<ApiListResponse<MovieItem>>

    @GET("movie/{id}")
    fun getDetail(@Path("id") id: Long): LiveData<ApiResponse<MovieDetail>>

    @GET("discover/movie?sort_by=release_date.desc")
    fun discoverLatestMovies(@Query("page") pageNo: Int, @Query("release_date.lte") maxReleaseDate: String?): LiveData<ApiListResponse<MovieItem>>
}