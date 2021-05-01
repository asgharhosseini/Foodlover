package ir.ah.app.foodlover.data.model.order

import android.annotation.SuppressLint
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
@Entity(tableName = "order_table")
data class Order(
        @SerializedName("id")
        @PrimaryKey(autoGenerate = true)
        var id: Int? = null,
        @SerializedName("title")
        var title: String,
        @SerializedName("image")
        var image: String,
        @SerializedName("price")
        var price: String,
        @SerializedName("count")
        var count: Int = 1
) : Parcelable
