package ir.mohsenafshar.apps.kdsample.di

import android.content.Context
import ir.mohsenafshar.apps.kdsample.data.MovieDataSource
import ir.mohsenafshar.apps.kdsample.data.MovieRepositoryImpl
import ir.mohsenafshar.apps.kdsample.data.remote.RemoteMovieDataSourceImpl
import ir.mohsenafshar.apps.kdsample.domain.repository.MovieRepository
import ir.mohsenafshar.apps.kdsample.domain.usecase.GetDetailMovieUseCase
import ir.mohsenafshar.apps.kdsample.domain.usecase.GetMovieListUseCase
import ir.mohsenafshar.apps.kdsample.ui.detail.DetailViewModel
import ir.mohsenafshar.apps.kdsample.ui.main.MainViewModel
import ir.mohsenafshar.apps.kdsample.util.data.GlobalApiErrorHandler
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    factory { (c: Context) -> GlobalApiErrorHandler(c) }

    single<MovieDataSource> { RemoteMovieDataSourceImpl(get()) }

    single<MovieRepository> { MovieRepositoryImpl(get()) }

    single { GetMovieListUseCase(get()) }
    single { GetDetailMovieUseCase(get()) }

    viewModel { MainViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}