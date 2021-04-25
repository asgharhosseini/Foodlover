package ir.ah.app.foodlover.data.remot.repositoeies

import ir.ah.app.foodlover.data.model.restaurant.Restaurant

interface RestaurantRepository {
    suspend fun getAllRestaurant(): List<Restaurant>
}