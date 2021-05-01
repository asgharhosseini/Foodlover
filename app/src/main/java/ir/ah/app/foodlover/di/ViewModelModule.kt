package ir.ah.app.foodlover.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import ir.ah.app.foodlover.data.loacal.OrderDao
import ir.ah.app.foodlover.data.remot.repositoeies.home.HomeRepositoryFake
import ir.ah.app.foodlover.data.remot.repositoeies.home.HomeRepositoryImpl
import ir.ah.app.foodlover.data.remot.repositoeies.restaurant.RestaurantRepositoryFake
import ir.ah.app.foodlover.data.remot.repositoeies.restaurant.RestaurantRepositoryImpl
import ir.ah.app.foodlover.data.remot.repositoeies.search.SearchRepositoryFake
import ir.ah.app.foodlover.data.remot.repositoeies.search.SearchRepositoryImpl


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

    @ViewModelScoped
    @Provides
    fun provideRestaurantRepositoryImpl(
        restaurantRepositoryFake: RestaurantRepositoryFake,
        database: OrderDao
    ) =
        RestaurantRepositoryImpl(restaurantRepositoryFake, database)

    @ViewModelScoped
    @Provides
    fun provideRestaurantRepositoryFake() = RestaurantRepositoryFake()

}