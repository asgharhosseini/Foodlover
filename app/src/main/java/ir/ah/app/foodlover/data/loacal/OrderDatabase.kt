package ir.ah.app.foodlover.data.loacal

import androidx.room.Database
import androidx.room.RoomDatabase
import ir.ah.app.foodlover.data.model.order.Order

@Database(entities = [Order::class], version = 1)
abstract class OrderDatabase : RoomDatabase() {
    abstract fun orderDao(): OrderDao
}