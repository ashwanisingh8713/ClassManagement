import com.indusjs.cm.app.App
import platform.UIKit.UIViewController
import androidx.compose.ui.window.ComposeUIViewController
import com.indusjs.platform.DataManager
import com.indusjs.platform.IDataStorage
import com.indusjs.platform.IOSDataStorage
import com.indusjs.platform.IOSSettingsStorage
import com.indusjs.platform.ISettingsStorage
import org.koin.dsl.module

fun MainViewController(): UIViewController = ComposeUIViewController {

    App()
}

// It is used to create a single instance of DataManager and provide it to the app.
// Inside iOSApp.kt, AppKoinKt.doInitKoin(module: MainKt.iosAppModule)
val iosAppModule = module {
    single<IDataStorage> { IOSDataStorage() }
    single<ISettingsStorage> { IOSSettingsStorage() }
    single { DataManager(get(), get()) }
}

