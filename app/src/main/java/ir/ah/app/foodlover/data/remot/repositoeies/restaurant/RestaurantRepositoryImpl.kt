package ir.ah.app.foodlover.data.remot.repositoeies.restaurant

import androidx.lifecycle.LiveData
import ir.ah.app.foodlover.data.loacal.OrderDao
import ir.ah.app.foodlover.data.model.order.Order
import ir.ah.app.foodlover.data.model.restaurant.Restaurant
import ir.ah.app.foodlover.data.remot.repositoeies.OrderRepository
import ir.ah.app.foodlover.data.remot.repositoeies.RestaurantRepository
import javax.inject.Inject

class RestaurantRepositoryImpl @Inject constructor(
    private val restaurantRepositoryFake: RestaurantRepositoryFake,
    private val database: OrderDao
) :
    RestaurantRepository, OrderRepository {
    override suspend fun getRestaurant(): Restaurant {
        return restaurantRepositoryFake.getRestaurant()
    }

    override suspend fun insertShoppingItem(order: Order) {
        database.insertShoppingItem(order)
    }

    override suspend fun deleteShoppingItem(order: Order) {
        database.deleteShoppingItem(order)
    }

    override fun observeAllOrderItems(): LiveData<List<Order>> {
        return database.observeAllOrderItems()
    }

    override fun observeTotalCount(): LiveData<Int> {
        return database.observeTotalCount()
    }

    override fun observeTotalSum(): LiveData<Float> {
        return database.observeTotalSum()
    }


}