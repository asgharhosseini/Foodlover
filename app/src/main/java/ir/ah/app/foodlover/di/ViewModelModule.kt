package ir.ah.app.foodlover.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import ir.ah.app.foodlover.data.remot.repositoeies.home.HomeRepositoryFake
import ir.ah.app.foodlover.data.remot.repositoeies.home.HomeRepositoryImpl


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

}