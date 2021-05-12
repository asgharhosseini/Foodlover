package ir.ah.app.foodlover.data.remot.repositoeies.restaurant

import androidx.lifecycle.*
import ir.ah.app.foodlover.data.loacal.*
import ir.ah.app.foodlover.data.model.order.*
import ir.ah.app.foodlover.data.model.restaurant.*
import ir.ah.app.foodlover.data.remot.repositoeies.*
import javax.inject.*

class RestaurantRepositoryImpl @Inject constructor(
    private val restaurantRepositoryFake: RestaurantRepositoryFake,
    private val database: OrderDao
) :
    RestaurantRepository, OrderRepository {
    override suspend fun getRestaurant(restaurantId: Int): Restaurant {
        return restaurantRepositoryFake.getRestaurant(restaurantId)
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