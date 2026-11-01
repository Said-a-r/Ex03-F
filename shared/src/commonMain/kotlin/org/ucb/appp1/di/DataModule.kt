package org.ucb.appp1.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.ucb.appp1.config.AppDatabase
import org.ucb.appp1.exchange.data.datasource.FirestoreDataSource
import org.ucb.appp1.config.data.repository.ConfigRepository
import org.ucb.appp1.novedades.data.repository.NovedadRepository
import org.ucb.appp1.exchange.data.datasource.RealTimeDataBase
import org.ucb.appp1.exchange.data.datasource.RemoteConfigDataSource
import org.ucb.appp1.exchange.data.repository.ExchangeRepositoryImpl
import org.ucb.appp1.exchange.domain.repository.ExchangeRepository
import org.ucb.appp1.userinformation.data.datasource.GithubRemoteDataSource
import org.ucb.appp1.userinformation.data.repository.GithubRepositoryImpl
import org.ucb.appp1.userinformation.data.service.GitHubApiService
import org.ucb.appp1.userinformation.domain.repository.GithubRepository

val dataModule = module {
    single { get<AppDatabase>().dollarDao() }
    single { get<AppDatabase>().configDao() }
    single { get<AppDatabase>().novedadDao() }
    singleOf(::ConfigRepository)
    singleOf(::NovedadRepository)

    single<GithubRemoteDataSource> { GitHubApiService() }
    single<GithubRepository> { GithubRepositoryImpl(get()) }

    singleOf(::RealTimeDataBase)
    singleOf(::RemoteConfigDataSource)

    single<ExchangeRepository> { ExchangeRepositoryImpl(get(), get()) }
    singleOf(::FirestoreDataSource)
}
