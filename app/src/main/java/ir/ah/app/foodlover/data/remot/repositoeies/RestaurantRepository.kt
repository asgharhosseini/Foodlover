package ir.ah.app.foodlover.data.remot.repositoeies

import ir.ah.app.foodlover.data.model.restaurant.*

interface RestaurantRepository {
    suspend fun getRestaurant(restaurantId: Int): Restaurant
}