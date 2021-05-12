package ir.mohsenafshar.apps.kdsample.di

import android.content.Context
import ir.mohsenafshar.apps.kdsample.data.DataSource
import ir.mohsenafshar.apps.kdsample.data.RepositoryImpl
import ir.mohsenafshar.apps.kdsample.data.remote.RemoteDataSourceImpl
import ir.mohsenafshar.apps.kdsample.domain.repository.Repository
import ir.mohsenafshar.apps.kdsample.domain.usecase.GetDetailUseCase
import ir.mohsenafshar.apps.kdsample.domain.usecase.GetModelListUseCase
import ir.mohsenafshar.apps.kdsample.ui.detail.DetailViewModel
import ir.mohsenafshar.apps.kdsample.ui.main.MainViewModel
import ir.mohsenafshar.apps.kdsample.util.data.GlobalApiErrorHandler
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    factory { (c: Context) -> GlobalApiErrorHandler(c) }

    single<DataSource> { RemoteDataSourceImpl(get()) }

    single<Repository> { RepositoryImpl(get()) }

    single { GetModelListUseCase(get()) }
    single { GetDetailUseCase(get()) }

    viewModel { MainViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}