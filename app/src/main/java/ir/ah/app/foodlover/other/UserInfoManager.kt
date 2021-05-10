package ir.ah.app.foodlover.other

import android.content.*
import javax.inject.*


class UserInfoManager @Inject constructor(private val sharedPreferences: SharedPreferences) {


    fun saveUser(email: String, token: String) {
        val editor = sharedPreferences.edit()
        editor.putString("email", email)
        editor.putString("token", token)
        editor.apply()
    }

    fun saveLocation(latitude: Double, longitude: Double, locationName: String? = null) {
        val editor = sharedPreferences.edit()
        editor.putString("latitude", latitude.toString())
        editor.putString("longitude", longitude.toString())
        editor.putString("locationName", locationName)
        editor.apply()

    }


    fun getLatitude(): String? {
        return sharedPreferences.getString("latitude", null)
    }

    fun getLongitude(): String? {
        return sharedPreferences.getString("longitude", null)
    }

    fun getLocationName(): String? {
        return sharedPreferences.getString("locationName", null)
    }

    fun getToken(): String? {
        return sharedPreferences.getString("token", null)
    }

    fun getEmail(): String? {
        return sharedPreferences.getString("email", null)
    }

    fun clear() = sharedPreferences.edit().clear().apply()


}