package ir.mohsenafshar.apps.kdsample.domain.repository

import androidx.lifecycle.LiveData
import ir.mohsenafshar.apps.kdsample.domain.dto.GetMovieParams
import ir.mohsenafshar.apps.kdsample.domain.entity.movie.MovieItem
import ir.mohsenafshar.apps.kdsample.domain.entity.movie.MovieDetail
import ir.mohsenafshar.apps.kdsample.util.data.Resource

interface MovieRepository {
    fun discoverLatestMovies(params: GetMovieParams): LiveData<Resource<List<MovieItem>>>
    fun getDetail(id: Long): LiveData<Resource<MovieDetail>>
}