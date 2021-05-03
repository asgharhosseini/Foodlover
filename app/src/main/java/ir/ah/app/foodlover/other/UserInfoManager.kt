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


    fun getToken(): String? {
        return sharedPreferences.getString("token", null)
    }

    fun getEmail(): String? {
        return sharedPreferences.getString("email", null)
    }

    fun clear() = sharedPreferences.edit().clear().apply()


}