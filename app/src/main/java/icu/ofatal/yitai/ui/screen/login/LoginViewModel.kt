package icu.ofatal.yitai.ui.screen.login

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.content.edit
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import icu.ofatal.yitai.data.model.UserRole
import icu.ofatal.yitai.sharedPreferencesOf
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val context: Application
): ViewModel() {
    var username by mutableStateOf("")
    var password by mutableStateOf("")

    var isLoginState by mutableStateOf(false)
    var role by mutableStateOf(UserRole.VISITOR)


    init {
        val sharedPreferences = context.sharedPreferencesOf("session")
        username = sharedPreferences.getString("username", "")!!
        password = sharedPreferences.getString("password", "")!!
    }

    fun login(result: (success: Boolean) -> Unit) {
        val sharedPreferences = context.sharedPreferencesOf("session")
        sharedPreferences.edit {
            putString("username", username)
            putString("password", password)
        }
        role = when (username) {
            "user1" ->
                UserRole.COMMON
            "user2" ->
                UserRole.DOCTOR
            else ->
                UserRole.VISITOR
        }

        isLoginState = role != UserRole.VISITOR
        result(isLoginState)
    }
}