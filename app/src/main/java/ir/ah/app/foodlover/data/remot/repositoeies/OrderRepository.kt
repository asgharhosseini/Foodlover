package ir.ah.app.foodlover.data.remot.repositoeies

import androidx.lifecycle.LiveData
import ir.ah.app.foodlover.data.model.order.Order

interface OrderRepository {

    suspend fun insertShoppingItem(order: Order)
    suspend fun deleteShoppingItem(order: Order)
    fun observeAllOrderItems(): LiveData<List<Order>>
    fun observeTotalCount(): LiveData<Int>
    fun observeTotalSum(): LiveData<Float>

}