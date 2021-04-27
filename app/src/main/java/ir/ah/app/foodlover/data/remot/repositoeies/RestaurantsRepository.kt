package ir.ah.app.foodlover.data.remot.repositoeies

import ir.ah.app.foodlover.data.model.restaurant.*

interface RestaurantsRepository {
    suspend fun getAllRestaurant(): List<Restaurant>
}