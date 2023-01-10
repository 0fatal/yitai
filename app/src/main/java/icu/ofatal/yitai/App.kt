package icu.ofatal.yitai

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.tencent.mmkv.MMKV
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AppContext : Application() {
    override fun onCreate() {
        super.onCreate()
        MMKV.initialize(this);
    }
}

/**
 * 使用顶层函数直接获取 SharedPreference
 *
 * @param name SharedPreference名字
 * @return SharedPreferences实例
 */
fun Context.sharedPreferencesOf(name: String): SharedPreferences = getSharedPreferences(name, Context.MODE_PRIVATE)
