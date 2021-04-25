package ir.ah.app.foodlover.data.model.restaurant

import android.annotation.*
import android.os.*
import com.google.gson.annotations.*
import kotlinx.android.parcel.*

@SuppressLint("ParcelCreator")
@Parcelize
data class Restaurant(
        @SerializedName("id")
        var id: Int,
        @SerializedName("title")
        var title: String,
        @SerializedName("categories")
        var categories: String,
        @SerializedName("rate")
        var rate: Int,
        @SerializedName("distance")
        var distance: String,
        @SerializedName("time_distance")
        var timeDistance: String,
        @SerializedName("image")
        var image: String
): Parcelable