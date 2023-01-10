package icu.ofatal.yitai.ui.screen.login

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

import icu.ofatal.yitai.R
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

import icu.ofatal.yitai.data.model.UserRole

@Composable
fun LoginScreen(loginViewModel: LoginViewModel = hiltViewModel(), navController: NavController) {
    val context = LocalContext.current

    // 登录进度对话框
    var progressDialog by remember {
        mutableStateOf(false)
    }

    var failedDialog by remember {
        mutableStateOf(false)
    }

    if (progressDialog) {
        AlertDialog(
            onDismissRequest = { progressDialog = false },
            title = { Text("登录中") },
            icon = { CircularProgressIndicator(Modifier.size(30.dp)) },
            text = { Text("请稍候...") },
            confirmButton = {}
        )
    }

    if (failedDialog) {
        AlertDialog(
            onDismissRequest = { failedDialog = false },
            title = { Text("登录失败") },
            text = { Text("登录失败") },
            confirmButton = {
                TextButton(
                    onClick = { failedDialog = false }
                ) {
                    Text("好的")
                }
            }
        )
    }

    Column() {
        Text(text = stringResource(id = R.string.app_name))
        Text(text = stringResource(id = R.string.app_intro))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = loginViewModel.username,
            onValueChange = {
                loginViewModel.username = it.trim().replace("\n", "")
            },
            placeholder = { Text("输入密码") })
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = loginViewModel.password,
            onValueChange = {
                loginViewModel.password = it.trim().replace("\n", "")
            },
            placeholder = { Text("输入密码") })
        OutlinedButton(onClick = {
            doLogin(
                context = context,
                loginViewModel = loginViewModel,
                navController = navController,
                err = { failedDialog = true },
                loading = { failedDialog = it })
        }) {
            Text("登录")
        }

    }
}


private fun doLogin(
    context: Context,
    loginViewModel: LoginViewModel,
    navController: NavController,
    err: () -> Unit,
    loading: (isLoading: Boolean) -> Unit
) {
    if (loginViewModel.username.isBlank() || loginViewModel.password.isBlank()) {
        Toast.makeText(context, "用户名或密码不能为空", Toast.LENGTH_SHORT).show()
        return
    }
    loading(true)
    loginViewModel.login {
        loading(false)
        if (!it) {
            err()
            return@login
        }
        navController.navigate(if (loginViewModel.role == UserRole.COMMON) "common/index" else "doctor/index") {
            popUpTo("login") {
                inclusive = true
            }
        }
    }
}