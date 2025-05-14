package com.indusjs.platform

import android.content.Context
import android.widget.Toast
import com.indusjs.cm.AndroidApp
import org.koin.dsl.module

actual val platform: Platform = Platform.Android

actual fun showToast(message: String) {
    // Android-specific implementation to show a toast message
    Toast.makeText(
        AndroidApp.instance,
        message,
        Toast.LENGTH_SHORT
    ).show()
}

val appModuleDS = module {
    single { AndroidDataStorage(get<AndroidApp>().applicationContext) }
    single { AndroidSettingsStorage(get<AndroidApp>().applicationContext) }
    single { DataManager(get(), get()) }
}