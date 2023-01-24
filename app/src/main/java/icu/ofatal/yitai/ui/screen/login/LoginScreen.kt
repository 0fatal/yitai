package icu.ofatal.yitai.ui.screen.login

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import icu.ofatal.yitai.R
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

import icu.ofatal.yitai.data.model.UserRole
import icu.ofatal.yitai.ui.component.GradientButton

@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = hiltViewModel(),
    navController: NavController,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(
                id = R.drawable.login_background,
            ),
            contentScale = ContentScale.Crop,
            contentDescription = "login_background",
            modifier = Modifier.fillMaxSize()
        )
        Content(
            loginViewModel = loginViewModel,
            navController = navController,
            modifier = Modifier.padding(32.dp)
        )
    }

}

@Composable
fun Content(
    loginViewModel: LoginViewModel,
    navController: NavController,
    modifier: Modifier = Modifier
) {
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

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier) {
        Text(
            text = stringResource(id = R.string.app_name),
            fontSize = 72.sp,
            modifier = Modifier.padding(top = 80.dp),
            fontFamily = FontFamily(
                listOf(Font(R.font.alimamashuhiti_bold))
            )
        )

        Text(
            text = stringResource(id = R.string.app_intro),
            fontSize = 18.sp,
            modifier = Modifier.padding(top = 12.dp, bottom = 70.dp)
        )

        CustomTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            value = loginViewModel.username,
            onValueChange = {
                loginViewModel.username = it.trim().replace("\n", "")
            },
            placeholder = { Text("输入用户名", fontSize = 12.sp) },
        )


        CustomTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),

            value = loginViewModel.password,
            onValueChange = {
                loginViewModel.password = it.trim().replace("\n", "")
            },
            placeholder = { Text("输入密码", fontSize = 12.sp) },

            )
        Spacer(modifier = Modifier.height(12.dp))

        GradientButton(
            gradient = Brush.verticalGradient(
                listOf(
                    Color(0xFFF794A5),
                    Color(0xFFFDD6BD)
                )
            ),
            onClick = {
//                doLogin(
//                    context = context,
//                    loginViewModel = loginViewModel,
//                    navController = navController,
//                    err = { failedDialog = true },
//                    loading = { failedDialog = it })
                Toast.makeText(context, "登录成功", Toast.LENGTH_SHORT).show()
                navController.navigate("common/index") {
                    popUpTo("login") {
                        inclusive = true
                    }
                }
            },
            modifier = Modifier.padding(horizontal = 30.dp, vertical = 8.dp)
        ) {
            Text(
                "普通端登录",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                letterSpacing = 1.sp
            )
        }
        GradientButton(
            gradient = Brush.verticalGradient(
                listOf(
                    Color(0xFF66A6FF),
                    Color(0xFF8AF7FF),
                )
            ),
            onClick = {
                Toast.makeText(context, "登录成功", Toast.LENGTH_SHORT).show()
                navController.navigate("doctor/index") {
                    popUpTo("login") {
                        inclusive = true
                    }
                }
            },
            modifier = Modifier.padding(horizontal = 30.dp, vertical = 8.dp)
        ) {
            Text(
                "医师端登录",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                letterSpacing = 1.sp
            )
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