package ir.ah.app.foodlover.di

import dagger.*
import dagger.hilt.*
import dagger.hilt.android.components.*
import dagger.hilt.android.scopes.*
import ir.ah.app.foodlover.data.loacal.*
import ir.ah.app.foodlover.data.remot.repositoeies.home.*
import ir.ah.app.foodlover.data.remot.repositoeies.restaurant.*
import ir.ah.app.foodlover.data.remot.repositoeies.search.*
import ir.ah.app.foodlover.other.*


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
    fun provideRestaurantRepositoryFake(userInfoManager: UserInfoManager) =
        RestaurantRepositoryFake(userInfoManager)

}