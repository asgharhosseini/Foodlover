package ir.ah.app.foodlover.data.model.banner

import android.annotation.*
import android.os.*
import com.google.gson.annotations.*
import kotlinx.android.parcel.*

@SuppressLint("ParcelCreator")
@Parcelize
data class Banner(
        @SerializedName("id")
        var id: Int,
        @SerializedName("title")
        var title: String,
        @SerializedName("subtitle")
        var subtitle: String,
        @SerializedName("image")
        var image: String,
        @SerializedName("discount")
        var discount: String,
        @SerializedName("color")
        var color: String,
        @SerializedName("code")
        var code: String
): Parcelable
