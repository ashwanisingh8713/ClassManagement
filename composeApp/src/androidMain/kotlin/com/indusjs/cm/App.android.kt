package com.indusjs.cm

import android.app.Application
import android.content.Context
import android.content.pm.ApplicationInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.indusjs.cm.app.App
import com.indusjs.cm.di.initKoin
import com.indusjs.platform.AndroidDataStorage
import com.indusjs.platform.AndroidSettingsStorage
import com.indusjs.platform.DataManager
import com.indusjs.platform.IDataStorage
import com.indusjs.platform.ISettingsStorage
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

class AndroidApp : Application() {
    companion object {
        lateinit var instance: AndroidApp
            private set
    }
    lateinit var dataManager: DataManager
        private set

    private val androidAppModule = module {
        single<IDataStorage> { AndroidDataStorage(instance.applicationContext) }
        single<ISettingsStorage> { AndroidSettingsStorage(instance.applicationContext) }
        single { DataManager(get(), get()) }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        initKoin {
            androidLogger(if (isDebug()) Level.ERROR else Level.NONE)
            androidContext(this@AndroidApp)
            modules(androidAppModule)
        }
        val dataStorage = AndroidDataStorage(applicationContext)
        val settingsStorage = AndroidSettingsStorage(applicationContext)
        dataManager = DataManager(dataStorage, settingsStorage)
    }
}
fun Context.isDebug() = 0 != applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE

class AppActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent { App() }
    }
}

@Preview
@Composable
fun AppPreview() { App() }
