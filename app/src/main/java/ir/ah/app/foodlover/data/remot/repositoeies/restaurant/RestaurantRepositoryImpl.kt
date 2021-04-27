package ir.ah.app.foodlover.data.remot.repositoeies.restaurant

import ir.ah.app.foodlover.data.model.restaurant.*
import ir.ah.app.foodlover.data.remot.repositoeies.*
import javax.inject.*

class RestaurantRepositoryImpl @Inject constructor(private val restaurantRepositoryFake: RestaurantRepositoryFake) :
        RestaurantRepository {
    override suspend fun getRestaurant(): Restaurant {
        return restaurantRepositoryFake.getRestaurant()
    }


}