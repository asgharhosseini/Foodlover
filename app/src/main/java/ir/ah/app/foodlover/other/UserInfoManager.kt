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

    fun saveUserInfo(fullName: String, address: String) {
        val editor = sharedPreferences.edit()
        editor.putString("fullName", fullName)
        editor.putString("address", address)
        editor.apply()

    }


    fun getFullName(): String? {
        return sharedPreferences.getString("fullName", null)
    }

    fun getLatitude(): String? {
        return sharedPreferences.getString("latitude", null)
    }

    fun getAddress(): String? {
        return sharedPreferences.getString("address", null)
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