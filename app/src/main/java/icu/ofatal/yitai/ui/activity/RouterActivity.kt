package icu.ofatal.yitai.ui.activity

import android.os.Build
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.navigation.NavBackStackEntry
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.tencent.mmkv.MMKV
import dagger.hilt.android.AndroidEntryPoint
import icu.ofatal.yitai.ui.local.LocalNavController
import icu.ofatal.yitai.ui.screen.case_data.CaseDataScreen
import icu.ofatal.yitai.ui.screen.community.CommunityScreen
import icu.ofatal.yitai.ui.screen.course.CourseScreen
import icu.ofatal.yitai.ui.screen.equipment.EquipmentAdjustScreen
import icu.ofatal.yitai.ui.screen.equipment.EquipmentScreen
import icu.ofatal.yitai.ui.screen.health.HealthScreen
import icu.ofatal.yitai.ui.screen.index.CommonIndexScreen
import icu.ofatal.yitai.ui.screen.index.DoctorIndexScreen
import icu.ofatal.yitai.ui.screen.login.LoginScreen
import icu.ofatal.yitai.ui.screen.notification.NotificationScreen
import icu.ofatal.yitai.ui.screen.timely_watch.TimelyWatchScreen
import icu.ofatal.yitai.ui.theme.YitaiTheme

@AndroidEntryPoint
class RouterActivity : AppCompatActivity() {
    val viewModel: RouterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            installSplashScreen()
//            splashScreen.setKeepOnScreenCondition { true }
        }

        // Night Mode
        MMKV.defaultMMKV().getInt("nightMode", 0).let {
            when (it) {
                0 -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                1 -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                2 -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                else -> error("bad const of night mode")
            }
        }

        setContent {
            val navController = rememberAnimatedNavController()
            CompositionLocalProvider(LocalNavController provides navController) {
                YitaiTheme {
                    AnimatedNavHost(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colors.background),
                        navController = navController,
                        startDestination = "login",
                        enterTransition = Transition.defaultEnterTransition,
                        exitTransition = Transition.defaultExitTransition,
                        popEnterTransition = Transition.defaultPopEnterTransition,
                        popExitTransition = Transition.defaultPopExitTransition
                    ) {
                        composable("common/index") {
                            CommonIndexScreen(navController = navController)
                        }

                        composable("doctor/index") {
                            DoctorIndexScreen(navController = navController)
                        }

                        composable("login") {
                            LoginScreen(navController = navController)
                        }

                        composable("notification") {
                            NotificationScreen()
                        }

                        composable("course") {
                            CourseScreen(navController = navController)
                        }

                        composable("equipment") {
                            EquipmentScreen()
                        }

                        composable("equipment/adjust") {
                            EquipmentAdjustScreen()
                        }

                        composable("timely-watch") {
                            TimelyWatchScreen(navController = navController)
                        }

                        composable("community") {
                            CommunityScreen(navController = navController)
                        }

                        composable("health") {
                            HealthScreen(navController = navController)
                        }

                        composable("case-data") {
                            CaseDataScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}

internal object Transition {
    val defaultEnterTransition: (AnimatedContentScope<NavBackStackEntry>.() -> EnterTransition) = {
        slideInHorizontally(
            initialOffsetX = {
                it
            },
            animationSpec = tween()
        )
    }

    val defaultExitTransition: (AnimatedContentScope<NavBackStackEntry>.() -> ExitTransition) = {
        slideOutHorizontally(
            targetOffsetX = {
                -it
            },
            animationSpec = tween()
        ) + fadeOut(
            animationSpec = tween()
        )
    }

    val defaultPopEnterTransition: (AnimatedContentScope<NavBackStackEntry>.() -> EnterTransition) =
        {
            slideInHorizontally(
                initialOffsetX = {
                    -it
                },
                animationSpec = tween()
            )
        }

    val defaultPopExitTransition: (AnimatedContentScope<NavBackStackEntry>.() -> ExitTransition) = {
        slideOutHorizontally(
            targetOffsetX = {
                it
            },
            animationSpec = tween()
        )
    }
}