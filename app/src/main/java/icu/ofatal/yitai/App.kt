package icu.ofatal.yitai

import android.app.Application
import com.tencent.mmkv.MMKV
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AppContext : Application() {
    override fun onCreate() {
        super.onCreate()
        MMKV.initialize(this);
    }
}