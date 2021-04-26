package ir.ah.app.foodlover.di

import dagger.*
import dagger.hilt.*
import dagger.hilt.android.components.*
import dagger.hilt.android.scopes.*
import ir.ah.app.foodlover.data.remot.repositoeies.home.*
import ir.ah.app.foodlover.data.remot.repositoeies.search.*


@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {


    @ViewModelScoped
    @Provides
    fun provideHomeRepositoryImpl(homeRepositoryFake: HomeRepositoryFake) =
            HomeRepositoryImpl(homeRepositoryFake)

    @ViewModelScoped
    @Provides
    fun provideHomeRepositoryFake() = HomeRepositoryFake()

    @ViewModelScoped
    @Provides
    fun provideSearchRepositoryImpl(searchRepositoryFake: SearchRepositoryFake) =
            SearchRepositoryImpl(searchRepositoryFake)

    @ViewModelScoped
    @Provides
    fun provideSearchRepositoryFake() = SearchRepositoryFake()

}