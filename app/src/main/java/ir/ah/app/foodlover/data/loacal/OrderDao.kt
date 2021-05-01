package ir.ah.app.foodlover.data.loacal

import androidx.lifecycle.LiveData
import androidx.room.*
import ir.ah.app.foodlover.data.model.order.Order

@Dao
interface OrderDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShoppingItem(order: Order)

    @Delete
    suspend fun deleteShoppingItem(order: Order)

    @Query("SELECT * FROM order_table")
    fun observeAllOrderItems(): LiveData<List<Order>>

    @Query("SELECT COUNT(title)  FROM order_table")
    fun observeTotalCount(): LiveData<Int>

    @Query("SELECT SUM(price)  FROM order_table")
    fun observeTotalSum(): LiveData<Float>


}