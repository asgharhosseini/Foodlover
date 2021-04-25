package ir.ah.app.foodlover.data.model.category

import android.annotation.*
import android.os.*
import com.google.gson.annotations.*
import kotlinx.android.parcel.*

@SuppressLint("ParcelCreator")
@Parcelize
data class Category(
        @SerializedName("id")
        var id: Int,
        @SerializedName("title")
        var title: String,
        @SerializedName("places")
        var places: Int,
        @SerializedName("image")
        var image: Int,
        @SerializedName("color")
        var color: String
): Parcelable
