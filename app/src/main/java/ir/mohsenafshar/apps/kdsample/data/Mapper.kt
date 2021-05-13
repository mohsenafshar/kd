package ir.mohsenafshar.apps.kdsample.data

import ir.mohsenafshar.apps.kdsample.data.remote.network.model.movie.GetMovieItem
import ir.mohsenafshar.apps.kdsample.domain.entity.movie.MovieItem

// this class is responsible for mapping Network Models to Domain Models
object Mapper {

    fun mapGetModelToDataModel(remoteGetMovieItem: GetMovieItem): MovieItem? {
//        return MovieItem(remoteGetMovieItem.adult, remoteGetMovieItem.backdrop_path, remoteGetMovieItem.genre_ids, remoteGetMovieItem.id)
        return null
    }

}