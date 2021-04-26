package ir.ah.app.foodlover.data.model.dessert

import android.annotation.*
import android.os.*
import com.google.gson.annotations.*
import kotlinx.android.parcel.*

@SuppressLint("ParcelCreator")
@Parcelize
data class Dessert(
        @SerializedName("id")
        var id: Int,
        @SerializedName("title")
        var title: String,
        @SerializedName("image")
        var image: String,
        @SerializedName("price")
        var price: String
) : Parcelable
