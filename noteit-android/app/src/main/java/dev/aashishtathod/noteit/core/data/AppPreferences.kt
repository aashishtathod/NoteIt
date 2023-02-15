package dev.aashishtathod.noteit.core.data

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

class AppPreferences @Inject constructor(private val context: Context) {
	
    companion object {
        private const val CUSTOMER_PREFS = "CUSTOMER_PREFS"
        private const val MODE = Context.MODE_PRIVATE
		
        private const val KEY_CUSTOMER_LOGIN_BASE_64_STR = "CUSTOMER_LOGIN_BASE_64_STR"
    }
	
    private val customerPreferences: SharedPreferences =
        context.getSharedPreferences(CUSTOMER_PREFS, MODE)
	
    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }
	
    val isLoggedIn: Boolean
        get() {
            val value = customerPreferences.getString(KEY_CUSTOMER_LOGIN_BASE_64_STR, "")
            return !value.isNullOrEmpty()
        }
	
    var userLoginToken: String?
        get() {
            return customerPreferences.getString(KEY_CUSTOMER_LOGIN_BASE_64_STR, "")
        }
        set(value) = customerPreferences.edit {
            it.putString(KEY_CUSTOMER_LOGIN_BASE_64_STR, value)
        }
}
