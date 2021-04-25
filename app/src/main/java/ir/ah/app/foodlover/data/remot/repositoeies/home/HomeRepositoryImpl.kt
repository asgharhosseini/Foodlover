package ir.ah.app.foodlover.data.remot.repositoeies.home

import ir.ah.app.foodlover.data.model.banner.Banner
import ir.ah.app.foodlover.data.model.category.Category
import ir.ah.app.foodlover.data.model.restaurant.Restaurant
import ir.ah.app.foodlover.data.remot.repositoeies.BannerRepository
import ir.ah.app.foodlover.data.remot.repositoeies.CategoryRepository
import ir.ah.app.foodlover.data.remot.repositoeies.RestaurantRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(private val homeRepositoryFake: HomeRepositoryFake) :
    RestaurantRepository, CategoryRepository, BannerRepository {
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