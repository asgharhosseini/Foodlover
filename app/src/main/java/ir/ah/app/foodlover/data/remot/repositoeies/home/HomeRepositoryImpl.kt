package ir.ah.app.foodlover.data.remot.repositoeies.home

import ir.ah.app.foodlover.data.model.banner.*
import ir.ah.app.foodlover.data.model.category.*
import ir.ah.app.foodlover.data.model.restaurant.*
import ir.ah.app.foodlover.data.remot.repositoeies.*
import javax.inject.*

class HomeRepositoryImpl @Inject constructor(private val homeRepositoryFake: HomeRepositoryFake) :
        RestaurantsRepository, CategoryRepository, BannerRepository {
    override suspend fun getAllRestaurant(): List<Restaurant> {
        return homeRepositoryFake.getAllRestaurant()
    }

    override suspend fun getAllCategory(): List<Category> {
        return homeRepositoryFake.getAllCategory()
    }

    override suspend fun getAllBanner(): List<Banner> {
        return homeRepositoryFake.getAllBanner()
    }
}